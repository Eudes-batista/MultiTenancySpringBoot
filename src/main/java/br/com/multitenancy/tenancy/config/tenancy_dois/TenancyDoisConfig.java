package br.com.multitenancy.tenancy.config.tenancy_dois;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "doisentityManagerFactory",
        basePackages = {"br.com.multitenancy.tenancy.repositorio.tenancy_dois"},
        transactionManagerRef = "doistransactionManager")
public class TenancyDoisConfig {

    @Autowired
    @Qualifier("datasource")
    private DataSource sourcePrimary;

    @Bean(name = "doisdatasource")
    public DataSource dataSource() {
        try {
            Connection build = sourcePrimary.getConnection();
            PreparedStatement prepareStatement = build.prepareStatement("select tenancy_id,banco from users");
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                var banco = resultSet.getString("banco");
                var tenancyId = resultSet.getString("tenancy_id");
                DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
                driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/" + banco);
                driverManagerDataSource.setUsername("root");
                driverManagerDataSource.setPassword("123456");
                driverManagerDataSource.setSchema(banco);
                TenantContext.getResolvers().put(tenancyId, driverManagerDataSource);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        TenancyAbstractImpl tenancyAbstractImpl = new TenancyAbstractImpl();
        tenancyAbstractImpl.setDefaultTargetDataSource(sourcePrimary);
        tenancyAbstractImpl.setTargetDataSources(TenantContext.getResolvers());
        return tenancyAbstractImpl;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }

    @Bean(name = "doisentityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
            EntityManagerFactoryBuilder managerFactoryBuilder,
            @Qualifier("doisdatasource") DataSource dataSource
    ) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("hibernate.ddl-auto", "update");
        parameters.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return managerFactoryBuilder.dataSource(dataSource)
                .properties(parameters)
                .packages("br.com.multitenancy.tenancy.modelo.tenancy_dois")
                .persistenceUnit("tenancyDois").build();
    }

    @Bean(name = "doistransactionManager")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("doisentityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}

package br.com.multitenancy.tenancy.config.tenancy;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
        basePackages = {"br.com.multitenancy.tenancy.repositorio"},
        transactionManagerRef = "transactionManager")
public class TenancyConfig {

    @Primary
    @Bean(name = "datasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/tenancy");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(
            EntityManagerFactoryBuilder managerFactoryBuilder,
            @Qualifier("datasource") DataSource dataSource
    ) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("hibernate.ddl-auto", "update");
        parameters.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        return managerFactoryBuilder.dataSource(dataSource)
                .properties(parameters)
                .packages("br.com.multitenancy.tenancy.modelo.tenancy")
                .persistenceUnit("tenancy").build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}

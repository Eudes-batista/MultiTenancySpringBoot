/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.multitenancy.tenancy.config.tenancy_dois;

import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author administrador
 */
@Configuration
public class FlywayInicialization {

    @PostConstruct
    public void flyway() {
        Map<Object, Object> resolvers = TenantContext.getResolvers();
        for (Map.Entry<Object, Object> entry : resolvers.entrySet()) {
            try {
                Object key = entry.getKey();
                Object val = entry.getValue();
                DataSource datasource = (DataSource) val;
                FluentConfiguration configure = Flyway.configure();
                configure.dataSource(datasource);
                String dados[] = datasource.getConnection().getMetaData().getURL().split("/");
                String banco = dados[3];
                configure.schemas(banco);
                configure.locations("classpath:db/migration2");
                configure.validateOnMigrate(true);
                configure.baselineOnMigrate(true);
                Flyway flyway = configure.load();
                flyway.migrate();
            } catch (SQLException ex) {
                Logger.getLogger(FlywayInicialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

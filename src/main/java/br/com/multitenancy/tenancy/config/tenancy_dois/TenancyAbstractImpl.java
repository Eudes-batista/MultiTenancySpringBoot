/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.multitenancy.tenancy.config.tenancy_dois;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 * @author administrador
 */
public class TenancyAbstractImpl extends AbstractRoutingDataSource{

    @Override
    protected Object determineCurrentLookupKey() {
        return TenantContext.getTenant();
    }
        
}

package br.com.multitenancy.tenancy.config.tenancy_dois;

import java.util.HashMap;
import java.util.Map;

//@ApplicationScope
//@Component
public class TenantContext {

    private static Map<Object, Object> resolvers = new HashMap<>();
    public static String TENANT_ID = "tenancy";
    
    public static String getTenant() {
        return TENANT_ID;
    }
    
    public static Map<Object, Object> getResolvers() {
        return resolvers;
    }
    

}

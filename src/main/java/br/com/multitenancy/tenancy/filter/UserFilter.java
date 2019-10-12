package br.com.multitenancy.tenancy.filter;

import br.com.multitenancy.tenancy.config.tenancy_dois.TenantContext;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) sr;
        TenantContext.TENANT_ID = httpServletRequest.getHeader("X-TENANCY-ID");
        fc.doFilter(sr, sr1);
    }
}

package kr.co.miroirs.smoking.security.config;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebAppInitializer extends AbstractSecurityWebApplicationInitializer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        logger.debug("before filter added");
        super.beforeSpringSecurityFilterChain(servletContext);
    }

    @Override
    protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
        logger.debug("after filter added: {}", servletContext.getFilterRegistrations());
        super.afterSpringSecurityFilterChain(servletContext);
    }

}

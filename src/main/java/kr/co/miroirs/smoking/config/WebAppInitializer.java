package kr.co.miroirs.smoking.config;

import javax.servlet.Filter;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import kr.co.miroirs.smoking.security.config.SecurityConfig;

@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ApplicationConfig.class, SecurityConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebMvcContextConfiguration.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter("UTF-8", true, true);        
        return new Filter[] { characterEncodingFilter };
    }
}

package kr.co.miroirs.smoking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.bedatadriven.jackson.datatype.jts.JtsModule;

import kr.co.miroirs.smoking.interceptor.LogInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"kr.co.miroirs.smoking.controller"})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

    private static final int CACHE_PERIOD = 31556926; // a year
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 20; // 20MB
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(CACHE_PERIOD);
        registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(CACHE_PERIOD);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(CACHE_PERIOD);
        registry.addResourceHandler("/htmls/**").addResourceLocations("/htmls/").setCachePeriod(CACHE_PERIOD);
        registry.addResourceHandler("/font/**").addResourceLocations("/font/").setCachePeriod(CACHE_PERIOD);
        registry.addResourceHandler("/pdf/**").addResourceLocations("/pdf/").setCachePeriod(CACHE_PERIOD);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("main");
    }
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public MultipartResolver multipartResolver() {
        org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = 
                new org.springframework.web.multipart.commons.CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(MAX_FILE_SIZE);
        return multipartResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor());
    }
    
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
            Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
            builder.modulesToInstall(new JtsModule());
            return builder;
    }
}

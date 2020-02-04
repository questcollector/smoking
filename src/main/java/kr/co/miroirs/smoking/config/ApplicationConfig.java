package kr.co.miroirs.smoking.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages={"kr.co.miroirs.smoking.dao",
        "kr.co.miroirs.smoking.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}

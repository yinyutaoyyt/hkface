package com.gizone.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yyt
 */
@SpringBootApplication( exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
public class GizoneClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GizoneClientApplication.class, args);
    }

}

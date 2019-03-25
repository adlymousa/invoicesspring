package com.harri.invoicesspring;

import com.harri.invoicesspring.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//swagger
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class InvoicesspringApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) { //for tomcat deployment - creating war/jar file
        return application.sources(InvoicesspringApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(InvoicesspringApplication.class, args);
        //run multiple springapp.run() in the main method
//        Object[] sources = {Application.class, ScheduledTasks.class};
//        SpringApplication.run(sources, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.harri.invoicesspring"))
                .paths(PathSelectors.any())
                .build();
    }

}

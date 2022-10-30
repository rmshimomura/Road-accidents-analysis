package com.uel.road_accidents_analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ProjectApplication extends SpringBootServletInitializer {

    /*
       TODO Remontar banco de dados
       TODO Implementar DAOs
       TODO LogController
       TODO Tratamento de arquivo
    * */

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProjectApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}

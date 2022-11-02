package com.uel.road_accidents_analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import java.util.Arrays;

@SpringBootApplication
public class ProjectApplication extends SpringBootServletInitializer {

    /*
       TODO Controllers no geral
       TODO Frontend
    * */

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProjectApplication.class);
    }

    @Bean
    ServletRegistrationBean jsfServletRegistration (ServletContext servletContext) {
        
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(Arrays.asList("*.xhtml"));
        srb.setLoadOnStartup(1);
        return srb;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

}

package com.uel.road_accidents_analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProjectApplication extends SpringBootServletInitializer {

    /*
       TODO Controllers no geral
       TODO Frontend
    * */

    public static void browse(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProjectApplication.class);
    }

    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        browse("http://localhost:8080/index.xhtml");
    }

    @Bean
    ServletRegistrationBean jsfServletRegistration(ServletContext servletContext) {

        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
        ServletRegistrationBean srb = new ServletRegistrationBean();
        srb.setServlet(new FacesServlet());
        srb.setUrlMappings(List.of("*.xhtml"));
        srb.setLoadOnStartup(1);
        return srb;
    }

    @Bean
    public FilterRegistrationBean FileUploadFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new org.primefaces.webapp.filter.FileUploadFilter());
        registration.setName("PrimeFaces FileUpload Filter");
        return registration;
    }

    @Bean
    public ServletContextInitializer initializer() {

        return servletContext -> {
            servletContext.setInitParameter(
                    "javax.faces.FACELETS_SKIP_COMMENTS", "true");
            servletContext.setInitParameter(
                    "com.sun.faces.expressionFactory",
                    "com.sun.el.ExpressionFactoryImpl");
            servletContext.setInitParameter("primefaces.UPLOADER",
                    "commons");
        };
    }

}

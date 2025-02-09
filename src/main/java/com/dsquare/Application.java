package com.dsquare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dsquare.configuration.AppConfig;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

@Theme(value = "dark")
@PWA(name = "Home for activities developing recording simple day of life", shortName = "Home of Dsquare")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class },scanBasePackages = {"com.dsquare.configuration","com.dsquare.service"}, scanBasePackageClasses = AppConfig.class)
@ComponentScan(basePackages = {"com.dsquare.api","com.dsquare.repository","com.dsquare.service","com.dsquare.view","com.dsquare.configuration","com.dsquare.security"})
@EnableJpaRepositories(basePackages = {"com.dsquare.repository"})
@EntityScan(basePackages = {"com.dsquare.model"})
public class Application  implements AppShellConfigurator {

	private static final long serialVersionUID = 6850028705495576466L;

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
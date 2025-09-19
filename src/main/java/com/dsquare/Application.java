package com.dsquare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import com.vaadin.flow.theme.Theme;

@Push
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class,ErrorMvcAutoConfiguration.class },scanBasePackages = {"com.dsquare.configuration","com.dsquare.service"})
@EnableJpaRepositories(basePackages = {"com.dsquare.repository"})
@ComponentScan(basePackages = {"com.dsquare.configuration","com.dsquare.service","com.dsquare.api","com.dsquare.page","com.dsquare.view"})
@EntityScan(basePackages = {"com.dsquare.repository","com.dsquare.model","com.dsquare.db"})
@PWA(name = "Home for activities developing recording simple day of life", shortName = "Home of Dsquare")
@Theme(value = "dark")
@SpringBootConfiguration
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

	private static final long serialVersionUID = 6850028705495576466L;

	public static void main(String[] args) {
      	SpringApplication.run(Application.class, args);
	}
}
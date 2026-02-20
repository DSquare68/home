package com.dsquare;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;

import com.dsquare.api.FootballApi;
import com.dsquare.component.StartService;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import com.vaadin.flow.theme.Theme;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

@Push
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class,ErrorMvcAutoConfiguration.class },scanBasePackages = {"com.dsquare.configuration","com.dsquare.service"})
@EnableJpaRepositories(basePackages = {"com.dsquare.repository"})
@ComponentScan(basePackages = {"com.dsquare.configuration","com.dsquare.service","com.dsquare.api","com.dsquare.page","com.dsquare.view","com.dsquare.db","com.dsquare.model","com.dsquare.component"})
@EntityScan(basePackages = {"com.dsquare.repository","com.dsquare.model","com.dsquare.db"})
@PWA(name = "Home for activities developing recording simple day of life", shortName = "Home of Dsquare")
@Theme(value = "dark")
@ConfigurationProperties
@SpringBootConfiguration
@EnableScheduling
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

	private static final long serialVersionUID = 6850028705495576466L;
	private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	
	public static void main(String[] args) {
      	SpringApplication.run(Application.class, args);
	}
}
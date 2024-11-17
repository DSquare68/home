package com.dsquare.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.dsquare.configuration.AppConfig;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.spring.annotation.EnableVaadin;
import com.vaadin.flow.theme.Theme;

@Push
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class },scanBasePackages = {"com.dsquare.configuration","com.dsquare.service"}, scanBasePackageClasses = AppConfig.class)
@PWA(name = "Home for activities developing recording simple day of life", shortName = "Home of Dsquare")
@Theme(value = "dark")
public class Application implements AppShellConfigurator {

	private static final long serialVersionUID = 6850028705495576466L;

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
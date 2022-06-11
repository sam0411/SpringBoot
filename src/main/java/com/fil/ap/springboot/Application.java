package com.fil.ap.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

/**
 * @SpringBootApplication is a convenience annotation that adds all of the following:
 * 
 * 	@Configuration tags the class as a source of bean definitions for the application context.
 * 	
 * 	@EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, 
 * 	other beans, and various property settings. Normally you would add @EnableWebMvc for a Spring MVC app, 
 * 	but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. 
 * 	This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
 * 	
 * 	@ComponentScan tells Spring to look for other components, configurations, 
 *  and services in the hello package, allowing it to find the controllers.
 *
 *	Start App: ./mvnw spring-boot:run
 *	Build runnable Jar: ./mvnw clean package
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	private DataSource dataSource;

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LOGGER.warn("Tomcat DataSource Connection Pool: " + dataSource);
	}
}

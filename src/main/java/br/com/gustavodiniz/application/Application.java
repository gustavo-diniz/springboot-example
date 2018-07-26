package br.com.gustavodiniz.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com")
public class Application {

	@Autowired
	private ServerProperties serverProperties;

	public void run(String... args) throws Exception {
		System.out.println(serverProperties);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
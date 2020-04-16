package com.sgu.springTask;

import com.sgu.springTask.web.config.AccountConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(AccountConfiguration.class)
public class SpringTaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringTaskApplication.class, args);
	}
}
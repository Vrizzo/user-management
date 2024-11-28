package com.mybank.user.query.api;

import com.mybank.user.core.configurations.AxonConfig;
import com.mybank.user.query.api.configurations.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class, SecurityConfig.class})
public class QueryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueryApiApplication.class, args);
	}

}

package com.mybank.user.cmd.api;

import com.mybank.user.cmd.api.configurations.SecurityConfig;
import com.mybank.user.core.configurations.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class, SecurityConfig.class})
public class UserCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCommandApplication.class, args);
    }

}

package com.yossavorn.accounts;

import com.yossavorn.accounts.dto.AccountContactInfo;
import com.yossavorn.accounts.listener.AuditAwareImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(
		title = "Accounts MicroService API",
		description = "Accounts MicroService API Documentation",
		version = "1.0",
		contact = @Contact(
				name = "Yossavorn",
				email = "yossavorn@gmail.com"
		)
))
@EnableConfigurationProperties(value = {AccountContactInfo.class})
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);

	}

}

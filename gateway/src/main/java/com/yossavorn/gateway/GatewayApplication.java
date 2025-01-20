package com.yossavorn.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p ->
						p.path("/easybank/accounts/**")
						.filters(f -> f.rewritePath("/easybank/accounts/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response_Date", LocalDateTime.now().toString()))
						.uri("lb://ACCOUNTS"))
				.route(p ->
						p.path("/easybank/cards/**")
						.filters(f -> f.rewritePath("/easybank/cards/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response_Date", LocalDateTime.now().toString()))
						.uri("lb://CARDS"))
				.route(p ->
						p.path("/easybank/loans/**")
						.filters(f -> f.rewritePath("/easybank/loans/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response_Date", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.build();
	}
}

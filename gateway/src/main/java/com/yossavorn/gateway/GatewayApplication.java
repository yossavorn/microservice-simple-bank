package com.yossavorn.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

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
						.filters(f -> f.rewritePath("/easyBank/accounts/(?<segment>.*)", "/${segment}"))
						.uri("lb://ACCOUNTS"))
				.route(p ->
						p.path("/easybank/cards/**")
						.filters(f -> f.rewritePath("/easyBank/cards/(?<segment>.*)", "/${segment}"))
						.uri("lb://CARDS"))
				.route(p ->
						p.path("/easybank/loans/**")
						.filters(f -> f.rewritePath("/easyBank/loans/(?<segment>.*)", "/${segment}"))
						.uri("lb://LOANS"))
				.build();
	}
}

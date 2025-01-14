package com.yossavorn.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix="accounts")
public record AccountContactInfo(String message, Map<String, String> contactDetails) {
}

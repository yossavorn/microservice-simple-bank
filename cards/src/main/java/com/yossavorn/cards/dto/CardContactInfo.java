package com.yossavorn.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix="cards")
public record CardContactInfo(String message, Map<String, String> contactDetails) {
}

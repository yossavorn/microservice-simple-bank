package com.yossavorn.loans.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix="loans")
@Getter@Setter
public class LoanContactInfo{
    private String message;
    private Map<String, String> contactDetails;
}

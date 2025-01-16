package com.yossavorn.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@ConfigurationProperties(prefix="accounts")
@Getter
@Setter
public class AccountContactInfo {

    private String message;
    private Map<String, String> contactDetails;

}

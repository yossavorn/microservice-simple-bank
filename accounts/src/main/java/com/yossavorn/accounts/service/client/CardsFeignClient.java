package com.yossavorn.accounts.service.client;

import com.yossavorn.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/api/v1/cards/{mobileNumber}",consumes = "application/json")
    ResponseEntity<CardDto> getOneCard(@PathVariable String mobileNumber) ;

}

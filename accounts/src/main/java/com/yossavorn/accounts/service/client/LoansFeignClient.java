package com.yossavorn.accounts.service.client;

import com.yossavorn.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping("/api/v1/loans/{mobileNumber}")
     ResponseEntity<LoansDto> getOneLoan(@PathVariable  String mobileNumber);
}

package com.yossavorn.accounts.controller;

import com.yossavorn.accounts.dto.CustomerDataDto;

import com.yossavorn.accounts.service.ICustomersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Customers", description = "Customers CRUD APIs")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private ICustomersService customersService;

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CustomerDataDto> getCustomerData(@PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {

        CustomerDataDto CustomerDataDto = customersService.getCustomerData(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(CustomerDataDto);
    }
}

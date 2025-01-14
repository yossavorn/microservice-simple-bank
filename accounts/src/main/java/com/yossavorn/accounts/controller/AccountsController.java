package com.yossavorn.accounts.controller;

import com.yossavorn.accounts.constants.AccountConstant;
import com.yossavorn.accounts.dto.AccountContactInfo;
import com.yossavorn.accounts.dto.CustomerDto;
import com.yossavorn.accounts.dto.ResponseDto;
import com.yossavorn.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Accounts", description = "Accounts CRUD APIs")
@RestController
@RequestMapping(path = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {


    private final IAccountService iAccountService;

    private final AccountContactInfo accountContactInfo;

    public AccountsController(IAccountService iAccountService, AccountContactInfo accountContactInfo) {
        this.iAccountService = iAccountService;
        this.accountContactInfo = accountContactInfo;
    }


    @Operation(
            summary = "Creates a new account for the given customer",
            description = "The request body contains the customer details such as name, email, and mobile number."
    )
    @ApiResponse(responseCode = "201", description = "Account created successfully")
    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {

        iAccountService.createAccount(customerDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }


    @Operation(
            summary = "Retrieves a customer by their mobile number",
            description = "Returns the customer details if found, otherwise returns a 404 Not Found response."
    )
    @ApiResponse(responseCode = "200", description = "Customer found successfully")
    @GetMapping("/get")
    public ResponseEntity<CustomerDto> getOneCustomer(@RequestParam   @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")  String mobileNumber){
        CustomerDto customerDto = iAccountService.getOneCustomer(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Updates an existing customer",
            description = "The request body contains the updated customer details such as name, email, and mobile number."
    )
    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
        iAccountService.updateCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
    }

    @Operation(
            summary = "Deletes a customer by their mobile number",
            description = "Deletes the customer if found"
    )
    @ApiResponse(responseCode = "200", description = "Customer deleted successfully")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCustomer(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
        iAccountService.deleteCustomer(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountContactInfo> getContactInfo() {
        return  ResponseEntity.status(HttpStatus.OK).body(accountContactInfo);
    }
}

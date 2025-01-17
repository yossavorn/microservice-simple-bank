package com.yossavorn.loans.controller;

import com.yossavorn.loans.constant.LoanConstant;
import com.yossavorn.loans.dto.LoanContactInfo;
import com.yossavorn.loans.dto.LoansDto;
import com.yossavorn.loans.dto.ResponseDto;
import com.yossavorn.loans.service.ILoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
@Validated

public class LoanController {
    private final ILoansService loanService;
    private final LoanContactInfo loanContactInfo;

    public LoanController(ILoansService loanService, LoanContactInfo loanContactInfo) {
        this.loanService = loanService;
        this.loanContactInfo = loanContactInfo;
    }
    @PostMapping()
    public ResponseEntity<ResponseDto> createLoan(@RequestBody @Valid LoansDto cardDto) {
        loanService.createLoan(cardDto);
        return ResponseEntity.ok().body(new ResponseDto(LoanConstant.STATUS_201, LoanConstant.MESSAGE_201));
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<LoansDto> getOneLoan(@PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {

        LoansDto cardDto = loanService.getOneLoan(mobileNumber);
        return ResponseEntity.ok().body(cardDto);

    }

    @PutMapping("/{mobileNumber}")
    public ResponseEntity<ResponseDto> updateLoan(@PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber, @RequestBody @Valid LoansDto cardDto) {
        loanService.updateLoan(mobileNumber, cardDto);
        return ResponseEntity.ok().body(new ResponseDto(LoanConstant.STATUS_200, LoanConstant.MESSAGE_200));

    }

    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<ResponseDto> deleteLoan(@PathVariable @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {

        loanService.deleteLoan(mobileNumber);
        return ResponseEntity.ok().body(new ResponseDto(LoanConstant.STATUS_200, LoanConstant.MESSAGE_200));

    }

    @GetMapping("/contact-info")
    public ResponseEntity<LoanContactInfo> getContactInfo() {
        return  ResponseEntity.status(HttpStatus.OK).body(loanContactInfo);
    }

}

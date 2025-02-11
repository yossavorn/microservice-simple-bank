package com.yossavorn.loans.service;

import com.yossavorn.loans.dto.LoansDto;
import org.springframework.stereotype.Service;


public interface ILoansService {

    void createLoan(LoansDto cardDto);

    LoansDto getOneLoan(String mobileNumber);

    void updateLoan(String mobileNumber, LoansDto cardDto);

    void deleteLoan(String mobileNumber);
}

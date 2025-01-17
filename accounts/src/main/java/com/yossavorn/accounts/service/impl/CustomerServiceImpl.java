package com.yossavorn.accounts.service.impl;

import com.yossavorn.accounts.dto.AccountDto;
import com.yossavorn.accounts.dto.CardDto;
import com.yossavorn.accounts.dto.CustomerDataDto;
import com.yossavorn.accounts.dto.LoansDto;
import com.yossavorn.accounts.entity.Account;
import com.yossavorn.accounts.entity.Customer;
import com.yossavorn.accounts.exception.NotFoundException;
import com.yossavorn.accounts.mapper.AccountMapper;
import com.yossavorn.accounts.mapper.CustomerMapper;
import com.yossavorn.accounts.repository.AccountRepository;
import com.yossavorn.accounts.repository.CustomerRepository;
import com.yossavorn.accounts.service.ICustomersService;
import com.yossavorn.accounts.service.client.CardsFeignClient;
import com.yossavorn.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomersService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;


    @Override
    public CustomerDataDto getCustomerData(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new NotFoundException("Customer","mobile number",mobileNumber)
        );

        Long customerId = customer.getCustomerId();

        Account account = accountRepository.findByCustomerId(customerId).orElseThrow(
                () -> new NotFoundException("Account","customerId",customerId.toString())
        );

        CustomerDataDto customerDataDto = CustomerMapper.mapToCustomerDataDto(customer, new CustomerDataDto());

        customerDataDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.getOneLoan(mobileNumber);

        customerDataDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardDto> cardDtoResponseEntity = cardsFeignClient.getOneCard(mobileNumber);

        customerDataDto.setCardDto(cardDtoResponseEntity.getBody());
        return customerDataDto;
    }
}

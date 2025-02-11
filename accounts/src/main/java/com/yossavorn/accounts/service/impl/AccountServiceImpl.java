package com.yossavorn.accounts.service.impl;

import com.yossavorn.accounts.constants.AccountConstant;
import com.yossavorn.accounts.dto.AccountDto;
import com.yossavorn.accounts.dto.CustomerDto;
import com.yossavorn.accounts.entity.Account;
import com.yossavorn.accounts.entity.Customer;
import com.yossavorn.accounts.exception.DuplicateCustomerException;
import com.yossavorn.accounts.exception.GlobalExceptionHandler;
import com.yossavorn.accounts.exception.NotFoundException;
import com.yossavorn.accounts.mapper.AccountMapper;
import com.yossavorn.accounts.mapper.CustomerMapper;
import com.yossavorn.accounts.repository.AccountRepository;
import com.yossavorn.accounts.repository.CustomerRepository;
import com.yossavorn.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);


    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        log.debug("Inside createAccount method with customer name: {} and mobile number: {}",
                customerDto.getName(), customerDto.getMobileNumber());
        Customer customerToSave = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerToSave.getMobileNumber());

        if (existingCustomer.isPresent()) {
            throw new DuplicateCustomerException("Customer with mobile number " + customerToSave.getMobileNumber() + " already exists");
        }

        customerToSave.setCreatedAt(LocalDateTime.now());
        customerToSave.setCreatedBy("Admin");
        Customer savedCustomer = customerRepository.save(customerToSave);

        Account account = this.createNewAccount(savedCustomer);
        accountRepository.save(account);
    }

    @Override
    public CustomerDto getOneCustomer(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new NotFoundException("Customer","mobile number",mobileNumber)
        );

        Long customerId = customer.getCustomerId();

        Account account = accountRepository.findByCustomerId(customerId).orElseThrow(
                () -> new NotFoundException("Account","customerId",customerId.toString())
        );

        AccountDto accountDto = AccountMapper.mapToAccountDto(account, new AccountDto());
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(accountDto);
        return customerDto;
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {

        AccountDto accountsDto = customerDto.getAccountDto();
        if(accountsDto !=null ){
            Account account = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new NotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccount(accountsDto, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new NotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);

        }

    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public void deleteCustomer(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new NotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());

        long newAccNumber = 1000000000L + new Random().nextInt(900000000);
        account.setAccountNumber(newAccNumber);
        account.setAccountType(AccountConstant.SAVINGS);
        account.setBranchAddress(AccountConstant.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Admin");


        return account;
    }
}

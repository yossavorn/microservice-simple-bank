package com.yossavorn.accounts.service;

import com.yossavorn.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDto the customer data transfer object containing
     *                    customer details such as name, email, and mobile number
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto getOneCustomer(String mobileNumber);

    void updateCustomer(CustomerDto customerDto);

    void deleteCustomer(String mobileNumber);
}

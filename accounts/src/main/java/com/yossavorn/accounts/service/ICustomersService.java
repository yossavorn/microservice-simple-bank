package com.yossavorn.accounts.service;

import com.yossavorn.accounts.dto.CustomerDataDto;

public interface ICustomersService {

    CustomerDataDto getCustomerData(String mobileNumber);
}

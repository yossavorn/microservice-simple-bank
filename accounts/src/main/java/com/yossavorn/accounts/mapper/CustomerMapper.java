package com.yossavorn.accounts.mapper;

import com.yossavorn.accounts.dto.CustomerDto;
import com.yossavorn.accounts.entity.Customer;

public class CustomerMapper {
    /**
     * Maps a {@link Customer} to a {@link CustomerDto}.
     * <p>
     * This method copies the name, email, and mobile number from the given
     * {@link Customer} to the given {@link CustomerDto}.
     * @param customer the {@link Customer} to map
     * @param customerDto the {@link CustomerDto} to update
     * @return the updated {@link CustomerDto}
     */
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    /**
     * Maps a {@link CustomerDto} to a {@link Customer}.
     * <p>
     * This method copies the name, email, and mobile number from the given
     * {@code customerDto} to the given {@code customer}.
     * <p>
     * @param customerDto the customer data transfer object
     * @param customer the customer entity
     * @return the customer entity
     */
    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}

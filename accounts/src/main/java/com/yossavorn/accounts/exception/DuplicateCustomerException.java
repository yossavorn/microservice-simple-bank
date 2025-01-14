package com.yossavorn.accounts.exception;

import com.yossavorn.accounts.constants.AccountConstant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicateCustomerException extends RuntimeException{

    public DuplicateCustomerException(String message) {
        super(message);
    }
}

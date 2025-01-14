package com.yossavorn.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String resourceName, String fieldName, String fieldValue){
        super(String.format("%s not found with the given %s:'%s'",resourceName,fieldName,fieldValue));
    }
}

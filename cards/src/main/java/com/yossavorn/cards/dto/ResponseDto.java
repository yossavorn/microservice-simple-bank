package com.yossavorn.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto  {

    private String statusCode;


    private String message;
}

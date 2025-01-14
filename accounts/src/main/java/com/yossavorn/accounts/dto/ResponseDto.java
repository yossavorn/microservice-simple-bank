package com.yossavorn.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response")
public class ResponseDto {

    @Schema(description = "Status code", example = "200")
    private String statusCode;

    @Schema(description = "Message", example = "Success")
    private String message;
}

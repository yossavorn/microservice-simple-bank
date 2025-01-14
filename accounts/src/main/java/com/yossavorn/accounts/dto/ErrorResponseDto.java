package com.yossavorn.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data@AllArgsConstructor
@Schema(
        name = "ErrorResponse"
)
public class ErrorResponseDto {

    @Schema(description = "API path invoked when the error occurred")
    private String apiPath;

    @Schema(description = "HTTP status code")
    private HttpStatus errorCode;

    @Schema(description = "Error message")
    private String errorMessage;

    @Schema(description = "Timestamp of the error")
    private LocalDateTime errorTime;
}

package com.yossavorn.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
    description = "Customer Data Transfer Object"
)
public class CustomerDto {

    @Schema(description = "Name of the customer", example = "John Doe")
    @NotEmpty(message="Name is required")
    @Size(min=2, max = 50, message="Name must be at least 2 characters long")
    private String name;

    @Schema(description = "Email of the customer", example = "2xk3o@example.com")
    @NotEmpty(message="Email is required")
    @Email(message="Invalid email format")
    private String email;

    @Schema(description = "Mobile number of the customer", example = "1234567890")
    @NotEmpty(message="Mobile Number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountDto accountDto;
}

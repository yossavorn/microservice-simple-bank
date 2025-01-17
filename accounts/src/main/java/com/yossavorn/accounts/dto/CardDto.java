package com.yossavorn.accounts.dto;



import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CardDto {

    @NotEmpty(message = "Mobile number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;


    @NotEmpty(message = "Card number is required")
    private String cardNumber;


    @NotEmpty(message = "Card type is required")
    private String cardType;


    @Positive(message = "Total totalLimit  greater than zero")
    @NotNull(message = "Total limit is required")
    private Long totalLimit;


    @NotNull(message = "Total amountUsed is required")
    @PositiveOrZero(message = "Total amountUsed should be equal or greater than zero")
    private Long amountUsed;


    @NotNull(message = "Total available amount is required")
    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private Long availableAmount;
}

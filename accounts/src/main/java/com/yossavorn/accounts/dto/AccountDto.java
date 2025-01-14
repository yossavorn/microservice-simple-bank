package com.yossavorn.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Account")
public class AccountDto {

    @Schema(description = "Account Number")
    @NotEmpty(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "AccountNumber must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Account Type")
    @NotEmpty(message = "AccountType can not be a null or empty")
    @Size(min = 1)
    private String accountType;

    @Schema(description = "Branch Address")
    @NotEmpty(message = "BranchAddress can not be a null or empty")
    @Size(min = 1)
    private String branchAddress;
}

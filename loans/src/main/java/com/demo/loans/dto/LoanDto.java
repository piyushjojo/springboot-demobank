package com.demo.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Loan info object", description = "Data object for transaction of loan")
public class LoanDto {

    @Schema(description = "mobile number")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be of 10 digits.")
    private String mobileNumber;

    @Schema(description = "Loan number")
    @NotEmpty(message = "loan number can not be empty.")
    private String loanNumber;

    @Schema(description = "loan type")
    @NotEmpty(message = "Loan type can not be empty.")
    private String loanType;

    @Schema(description = "total loan")
    @NotEmpty(message = "total loan can not be empty.")
    @PositiveOrZero(message = "total loan should be zero or more")
    private int totalLoan;

    @Schema(description = "total amount paid ")
    @NotEmpty(message = "amount paid can not be empty.")
    @PositiveOrZero(message = "amount paid should be zero or more")
    private int amountPaid;

    @Schema(description = "total outstanding amount ")
    @NotEmpty(message = "outstanding amount can not be empty.")
    @PositiveOrZero(message = "outstanding amount should be zero or more")
    private int outstandingAmount;
}

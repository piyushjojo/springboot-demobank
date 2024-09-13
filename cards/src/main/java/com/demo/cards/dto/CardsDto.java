package com.demo.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        description = "Card data handling object."
)
public class CardsDto {

    @NotEmpty(message = "mandatory field")
    @Pattern(regexp = "(^$|[0-9])")
    @Schema(description = "mobile number")
    private String mobileNumber;

    @NotEmpty(message = "mandatory field")
    @Schema(description = "card number")
    private String cardNumber ;

    @NotEmpty(message = "mandatory field")
    @Schema(description = "card type")
    private String cardType;

    @NotEmpty(message = "mandatory field")
    @Schema(description = "total card limit")
    private int totalLimit;

    @NotEmpty(message = "mandatory field")
    @Schema(description = "total amount used")
    private int amountUsed;

    @NotEmpty(message = "mandatory field")
    @Schema(description = "remaining amount")
    private int availableAmount;
}

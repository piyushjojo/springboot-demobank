package com.demo.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public class ResponseDto {

    @Schema(description = "Field to hold response code.")
    private String statusCode;

    @Schema(description = "Field to hold response message.")
    private String statusMsg;
}

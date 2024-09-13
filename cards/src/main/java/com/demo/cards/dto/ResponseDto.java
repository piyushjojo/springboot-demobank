package com.demo.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Response object")
public class ResponseDto {

    @Schema(description = "response status code")
    private String statusCode;

    @Schema(description = "response status message.")
    private String statusMessage;
}

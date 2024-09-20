package com.demo.loans.controller;

import com.demo.loans.constants.LoansConstants;
import com.demo.loans.dto.ErrorResponseDto;
import com.demo.loans.dto.LoanDto;
import com.demo.loans.dto.ResponseDto;
import com.demo.loans.service.ILoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Loan CRUD Functionalities",
        description = "API endpoints for CRUD operations for Loan create, update , retrieve or delete."
)
@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
public class LoansController {

    private ILoanService iLoanService ;

    @Operation(
            summary = "Create loan api endpoint.",
            description = "Create Loan in Demo bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "loan created successfully."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error. Account not created.",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})")
                                                      String mobileNumber){
        iLoanService.createLoan(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch loan info",
            description = "REST API endpoint to fetch loan info."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "loan fetched successfully."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error. Something unexpected occured.",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})")
                                                        String mobileNumber){
        LoanDto loanDto = iLoanService.fetchLoanData(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(loanDto);
    }

    @Operation(
            summary = "update loan info",
            description = "REST API endpoint to update loan info in demo bank."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Loan information updated."
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Loan information not updated."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoanDto loanDto){
        boolean detailsUpdated = iLoanService.updateLoanDetails(loanDto);
        if(detailsUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
    }

    @Operation(
            summary = "Delete loan info using mobile number",
            description = "REST API endpoint to delete loan info."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error. Info not deleted."
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})")
                                                             String mobileNumber){
        boolean loanIsDeleted = iLoanService.deleteLoanDetails(mobileNumber);
        if(loanIsDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
    }

}

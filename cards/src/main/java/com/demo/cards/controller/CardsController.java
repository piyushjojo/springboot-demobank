package com.demo.cards.controller;

import com.demo.cards.constants.CardsConstants;
import com.demo.cards.dto.CardsContactInfoDto;
import com.demo.cards.dto.CardsDto;
import com.demo.cards.dto.ErrorResponseDto;
import com.demo.cards.dto.ResponseDto;
import com.demo.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Cards CRUD Ops REST API endpoints.",
        description = "Cards CRUD Ops REST API endpoints."
)
@RestController
@RequestMapping("/api")
@Validated
public class CardsController {

    private ICardsService iCardsService ;

    public CardsController(ICardsService iCardsService){
        this.iCardsService = iCardsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private CardsContactInfoDto cardsContactInfoDto;

    @Operation(
            summary = "Create cards api endpoint.",
            description = "Create cards in Demo bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "cards created successfully."
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
    public ResponseEntity<ResponseDto> createCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})")
                                                  String mobileNumber){
        iCardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch cards info",
            description = "REST API endpoint to fetch cards info."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "cards info fetched successfully."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal error. Something unexpected occured..",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardData(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})")
                                                      String mobileNumber){
        CardsDto cardDto = iCardsService.fetchCardData(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(cardDto);
    }

    @Operation(
            summary = "update cards info",
            description = "REST API endpoint to update cards info in demo bank."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "cards information updated."
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "cards information not updated."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@RequestBody CardsDto cardsDto){
        boolean isUpdated = iCardsService.updateCardDetails(cardsDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
    }

    @Operation(
            summary = "Delete cards info using mobile number",
            description = "REST API endpoint to delete cards info."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully deleted."
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "cards information not deleted."
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error. Info not deleted."
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})")
                                                             String mobileNumber){
        boolean isDeleted = iCardsService.deleteCardDetails(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
    }

    @Operation(
            summary = "Fetch Build Info REST API",
            description = "REST API to fetch Build Info"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Operation(
            summary = "Fetch JAVA HOME Info REST API",
            description = "REST API to fetch JAVA HOME Info"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaHome(){
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }


    @Operation(
            summary = "Fetch Contact Details REST API",
            description = "REST API to fetch Contact details."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDto> getContactInfo(){
        return ResponseEntity.status(HttpStatus.OK).body(cardsContactInfoDto);
    }


}

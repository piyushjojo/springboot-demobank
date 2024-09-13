package com.demo.cards.controller;

import com.demo.cards.constants.CardsConstants;
import com.demo.cards.dto.CardsDto;
import com.demo.cards.dto.ResponseDto;
import com.demo.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Cards CRUD Ops REST API endpoints.",
        description = "Cards CRUD Ops REST API endpoints."
)
@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Validated
public class CardsController {

    private ICardsService iCardsService ;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})")
                                                  String mobileNumber){
        iCardsService.createCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardData(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})")
                                                      String mobileNumber){
        CardsDto cardDto = iCardsService.fetchCardData(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(cardDto);
    }

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
}

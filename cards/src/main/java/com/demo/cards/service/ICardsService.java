package com.demo.cards.service;

import com.demo.cards.dto.CardsDto;
import jakarta.validation.constraints.Pattern;

public interface ICardsService {
    void createCard(String mobileNumber);

    CardsDto fetchCardData(String mobileNumber);

    boolean updateCardDetails(CardsDto mobileNumber);

    boolean deleteCardDetails(String mobileNumber);
}

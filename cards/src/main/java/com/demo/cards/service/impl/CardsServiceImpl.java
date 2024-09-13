package com.demo.cards.service.impl;

import com.demo.cards.constants.CardsConstants;
import com.demo.cards.dto.CardsDto;
import com.demo.cards.entity.Cards;
import com.demo.cards.exception.CardAlreadyRegistered;
import com.demo.cards.exception.ResourceNotFoundException;
import com.demo.cards.mapper.CardsMapper;
import com.demo.cards.repository.CardsRepository;
import com.demo.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;


    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNumber);
        if(card.isPresent()){
            throw new CardAlreadyRegistered("Mobile number "+mobileNumber+" is already registed for card");
        }
        Cards newCard = createNewCard(mobileNumber);
        cardsRepository.save(newCard);
    }


    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        newCard.setMobileNumber(mobileNumber);
        return newCard;
    }


    @Override
    public CardsDto fetchCardData(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card Data", "Mobile Number", mobileNumber));

        return CardsMapper.mapToCardsDto(card, new CardsDto());
    }

    @Override
    public boolean updateCardDetails(CardsDto cardsDto) {
        Cards card = cardsRepository.findByMobileNumber(cardsDto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card Data", "Mobile Number", cardsDto.getMobileNumber()));
        CardsMapper.mapToCards(cardsDto,card);
        cardsRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCardDetails(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card Data", "Mobile Number", mobileNumber));
        cardsRepository.delete(card);
        return true;
    }


}

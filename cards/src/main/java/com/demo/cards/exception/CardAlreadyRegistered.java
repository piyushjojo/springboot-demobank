package com.demo.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CardAlreadyRegistered extends RuntimeException{

    public CardAlreadyRegistered(String message){
        super(message);
    }

}

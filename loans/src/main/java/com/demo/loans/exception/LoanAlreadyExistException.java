package com.demo.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class LoanAlreadyExistException extends RuntimeException{

    public LoanAlreadyExistException(String msg){
        super(msg);
    }
}

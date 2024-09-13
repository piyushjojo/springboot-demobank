package com.demo.cards.exception;

import com.demo.cards.dto.ErrorResponseDto;
import com.demo.cards.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex, WebRequest req){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                req.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseDto);
    }

    @ExceptionHandler(CardAlreadyRegistered.class)
    public ResponseEntity<ErrorResponseDto> handleCardAlreadyExistException(CardAlreadyRegistered ex, WebRequest req){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                req.getDescription(false),
                HttpStatus.EXPECTATION_FAILED,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(errorResponseDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> hanldeResourceNotFound(ResourceNotFoundException ex, WebRequest req){
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                req.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponseDto);
    }
}

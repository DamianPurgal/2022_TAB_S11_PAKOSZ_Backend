package com.example.skiSlope.api.handlers;

import com.example.skiSlope.api.handlers.DTO.ErrorResponse;
import com.example.skiSlope.exception.BusinessException;
import com.example.skiSlope.exception.JwtValidationException;
import com.example.skiSlope.exception.UserNotFoundException;
import com.example.skiSlope.exception.UserUsernameIsNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse businessExceptionHandler(BusinessException businessException){
        return new ErrorResponse(businessException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse userNotFoundExceptionHandler(UserNotFoundException UserNotFoundException){
        return new ErrorResponse(UserNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorResponse jwtValidationExceptionHandler(JwtValidationException jwtValidationException){
        return new ErrorResponse(jwtValidationException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse UserUsernameIsNotAvailableException(UserUsernameIsNotAvailableException userUsernameIsNotAvailableException) {
        return new ErrorResponse(userUsernameIsNotAvailableException);
    }
}

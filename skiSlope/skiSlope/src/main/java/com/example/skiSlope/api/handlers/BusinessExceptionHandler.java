package com.example.skiSlope.api.handlers;

import com.example.skiSlope.api.handlers.DTO.ErrorResponse;
import com.example.skiSlope.exception.*;
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

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse ExpireDateEarlierThanStartDateException(ExpireDateEarlierThanStartDateException expireDateEarlierThanStartDateException) {
        return new ErrorResponse(expireDateEarlierThanStartDateException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse NewStartDateBeforeStartDateException(NewStartDateBeforeStartDateException newStartDateBeforeStartDateException) {
        return new ErrorResponse(newStartDateBeforeStartDateException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse NoAvailableEntryOptionException(NoAvailableEntryOptionException noAvailableEntryOptionException) {
        return new ErrorResponse(noAvailableEntryOptionException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorResponse PriceGreaterThanFullPriceException(PriceGreaterThanFullPriceException priceGreaterThanFullPriceException) {
        return new ErrorResponse(priceGreaterThanFullPriceException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse PriceNotFoundException(PriceNotFoundException priceNotFoundException) {
        return new ErrorResponse(priceNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ErrorResponse ResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return new ErrorResponse(resourceNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse SkiLiftNotFoundException(SkiLiftNotFoundException skiLiftNotFoundException) {
        return new ErrorResponse(skiLiftNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse TicketNotFoundException(TicketNotFoundException ticketNotFoundException) {
        return new ErrorResponse(ticketNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse VoucherNotFoundException(VoucherNotFoundException voucherNotFoundException) {
        return new ErrorResponse(voucherNotFoundException);
    }



}

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
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse UserUsernameIsNotAvailableExceptionHandler(UserUsernameIsNotAvailableException userUsernameIsNotAvailableException) {
        return new ErrorResponse(userUsernameIsNotAvailableException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse ExpireDateEarlierThanStartDateExceptionHandler(ExpireDateEarlierThanStartDateException expireDateEarlierThanStartDateException) {
        return new ErrorResponse(expireDateEarlierThanStartDateException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse NewStartDateBeforeStartDateExceptionHandler(NewStartDateBeforeStartDateException newStartDateBeforeStartDateException) {
        return new ErrorResponse(newStartDateBeforeStartDateException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse NoAvailableEntryOptionExceptionHandler(NoAvailableEntryOptionException noAvailableEntryOptionException) {
        return new ErrorResponse(noAvailableEntryOptionException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse PriceGreaterThanFullPriceExceptionHandler(PriceGreaterThanFullPriceException priceGreaterThanFullPriceException) {
        return new ErrorResponse(priceGreaterThanFullPriceException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse PriceNotFoundExceptionHandler(PriceNotFoundException priceNotFoundException) {
        return new ErrorResponse(priceNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse ResourceNotFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException) {
        return new ErrorResponse(resourceNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse SkiLiftNotFoundExceptionHandler(SkiLiftNotFoundException skiLiftNotFoundException) {
        return new ErrorResponse(skiLiftNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse TicketNotFoundExceptionHandler(TicketNotFoundException ticketNotFoundException) {
        return new ErrorResponse(ticketNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse VoucherNotFoundException(VoucherNotFoundException voucherNotFoundException) {
        return new ErrorResponse(voucherNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse ScannerQRLoginIsNotAvailableExceptionHandler(ScannerQRLoginNotAvailableException scannerQRLoginNotAvailableException) {
        return new ErrorResponse(scannerQRLoginNotAvailableException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse ScannerQRNotFoundExceptionHandler(ScannerQRNotFoundException scannerNotFoundException) {
        return new ErrorResponse(scannerNotFoundException);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorResponse ScannerQRLoginFailureExceptionHandler(ScannerQRFailedToLoginException scannerQRFailedToLoginException) {
        return new ErrorResponse(scannerQRFailedToLoginException);
    }


}

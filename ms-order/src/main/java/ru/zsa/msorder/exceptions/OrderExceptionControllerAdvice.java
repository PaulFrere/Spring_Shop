package ru.zsa.msorder.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.zsa.msorder.exceptions.PromoInvalidException;

@Slf4j
@ControllerAdvice
public class OrderExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handlePromoInvalidException(PromoInvalidException e) {
        log.error(e.getMessage());
        MarketError err = new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}

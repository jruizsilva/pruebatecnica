package paygoal.pruebatecnica.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import paygoal.pruebatecnica.exceptions.CustomEntityNotFoundException;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(CustomEntityNotFoundException.class)
    public ResponseEntity<String> handleCustomEntityNotFoundException(CustomEntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body("Error: " + exception.getMessage());
    }

}

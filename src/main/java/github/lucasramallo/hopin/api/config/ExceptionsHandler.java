package github.lucasramallo.hopin.api.config;

import github.lucasramallo.hopin.core.domain.cab.exceptions.InvalidPlateException;
import github.lucasramallo.hopin.core.domain.customer.exceptions.CustomerNotFoundException;
import github.lucasramallo.hopin.core.domain.customer.exceptions.EmailAlreadyRegisteredException;
import github.lucasramallo.hopin.core.domain.driver.exceptions.DriverNotFoundException;
import github.lucasramallo.hopin.core.domain.driver.exceptions.UnderageDriverException;
import github.lucasramallo.hopin.core.globalExceptions.InvalidCredentialsException;
import github.lucasramallo.hopin.core.globalExceptions.InvalidEmailException;
import github.lucasramallo.hopin.core.globalExceptions.InvalidUserNameException;
import github.lucasramallo.hopin.core.globalExceptions.RequiredFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    //Customer

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<String> handrleEmailAlreadyRegistered(EmailAlreadyRegisteredException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFound(CustomerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    //Global

    @ExceptionHandler(InvalidUserNameException.class)
    public ResponseEntity<String> handleInvalidUserName(InvalidUserNameException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<String> handleInvalidEmail(InvalidEmailException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(RequiredFieldsException.class)
    public ResponseEntity<String> handleRequiredFields(RequiredFieldsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Diver

    @ExceptionHandler(UnderageDriverException.class)
    public ResponseEntity<String> handleUnderageDriver(UnderageDriverException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<String> handleDriverNotFound(DriverNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    //Cab

    @ExceptionHandler(InvalidPlateException.class)
    public ResponseEntity<String> handleInvalidPlate(InvalidPlateException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}

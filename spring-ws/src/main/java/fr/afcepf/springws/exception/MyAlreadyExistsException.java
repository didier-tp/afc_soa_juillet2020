package fr.afcepf.springws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //400 (or CONFLICT / 409)
public class MyAlreadyExistsException extends RuntimeException{
    public MyAlreadyExistsException() {
    }
    public MyAlreadyExistsException(String message) {
        super(message);
    }
    public MyAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    public MyAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

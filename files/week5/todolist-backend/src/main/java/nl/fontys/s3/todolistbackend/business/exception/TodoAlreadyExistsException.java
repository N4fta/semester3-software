package nl.fontys.s3.todolistbackend.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TodoAlreadyExistsException extends ResponseStatusException {
    public TodoAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "TODO_DUPLICATED");
    }
}

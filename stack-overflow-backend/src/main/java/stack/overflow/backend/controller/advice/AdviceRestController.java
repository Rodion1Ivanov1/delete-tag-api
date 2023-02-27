package stack.overflow.backend.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import stack.overflow.backend.api.Error;
import stack.overflow.backend.exception.EntityNotFoundException;
import stack.overflow.backend.exception.VoteCountLimitException;

@ControllerAdvice(annotations = RestController.class)
public class AdviceRestController {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> exception(Exception e) {
        return ResponseEntity.internalServerError().body(Error.build(e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Error> authenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.build(e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(VoteCountLimitException.class)
    public ResponseEntity<Error> voteCountLimitException(VoteCountLimitException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Error.build(e.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> entityNotFoundException(EntityNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.build(e.getMessage()));
    }
}

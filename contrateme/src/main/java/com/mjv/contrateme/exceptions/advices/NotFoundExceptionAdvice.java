package com.mjv.contrateme.exceptions.advices;

import com.mjv.contrateme.exceptions.NotFoundException;
import com.mjv.contrateme.exceptions.message.MessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class NotFoundExceptionAdvice {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<MessageException> NotFoundException(NotFoundException ex, HttpServletRequest request) {

        MessageException error = new MessageException(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
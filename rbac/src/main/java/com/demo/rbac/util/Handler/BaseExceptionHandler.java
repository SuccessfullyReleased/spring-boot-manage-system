package com.demo.rbac.util.Handler;

import com.demo.rbac.util.Exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity handle(Exception e) {
        if (e instanceof ConstraintViolationException) {
            return new ResponseEntity<>(e.getMessage().split(":")[1].trim(), HttpStatus.BAD_REQUEST);
        } else if (e instanceof BaseException) {
            return new ResponseEntity<>(e.getMessage(), ((BaseException) e).getStatus());
        } else {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

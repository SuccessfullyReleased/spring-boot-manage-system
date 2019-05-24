package com.demo.cms.util.Handler;

import com.demo.cms.util.Exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @author 戴俊明
 * @version 1.0
 * @className BaseExceptionHandler
 * @description 运行时异常全局处理类
 * @date 2019/5/20 19:05
 **/
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity handle(Exception e) {
        log.info("BaseExceptionHandler::handle::e = [{}]", e);
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

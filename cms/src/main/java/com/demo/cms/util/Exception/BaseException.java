package com.demo.cms.util.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * @author 戴俊明
 * @version 1.0
 * @className BaseException
 * @description 运行时异常的封装
 * @date 2019/5/20 19:03
 **/
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    /**
     * @author 戴俊明
     * @description 要返回的HTTP状态码
     * @date 2019/5/20 19:04
     **/
    private HttpStatus status;

    /**
     * @param message 报错信息
     * @param status  要返回的HTTP状态码
     * @author 戴俊明
     * @description BaseException构造函数
     * @date 2019/5/20 19:04
     **/
    public BaseException(String message, HttpStatus status) {
        super(message);
        log.info("BaseException::BaseException::message = [{}], status = [{}]", message, status);
        this.status = status;
    }
}

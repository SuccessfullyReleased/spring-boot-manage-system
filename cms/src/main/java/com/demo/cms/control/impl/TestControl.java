package com.demo.cms.control.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 戴俊明
 * @className TestControl
 * @description 测试
 * @date 2019/5/22 18:50
 **/

@Slf4j
@RestController
public class TestControl {

    @GetMapping(value = "welcome")
    String welcome(){
        return "hello";
    }

}

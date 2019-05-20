package com.demo.rbac.authorization.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 戴俊明
 * @version 1.0
 * @className Token
 * @description token的实体类
 * @date 2019/5/20 15:35
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {

    private String username;

    private String token;

}

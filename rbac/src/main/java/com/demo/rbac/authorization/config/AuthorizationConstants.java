package com.demo.rbac.authorization.config;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.KeyPair;

/**
 * @author 戴俊明
 * @version 1.0
 * @className AuthorizationConstants
 * @description 常量
 * @date 2019/5/20 15:22
 **/
public class AuthorizationConstants {

    /**
     * @author 戴俊明
     * @description 此次请求的用户
     * @date 2019/5/20 15:23
     **/
    public static final String CURRENT_USER = "CURRENT_USER";

    /**
     * @author 戴俊明
     * @description token有效期，小时
     * @date 2019/5/20 15:23
     **/
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * @author 戴俊明
     * @description 存放token的header字段
     * @date 2019/5/20 15:23
     **/
    public static final String AUTHORIZATION = "authorization";

}

package com.demo.rbac.authorization.config;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.RSA;

import java.security.KeyPair;

public class AuthorizationConstants {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER = "CURRENT_USER";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";

    public static final KeyPair keyPair = SecureUtil.generateKeyPair("RSA");

    public static final RSA rsa = new RSA(AuthorizationConstants.keyPair.getPrivate(), AuthorizationConstants.keyPair.getPublic());

}

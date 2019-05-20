package com.demo.rbac.authorization.manager;

import com.demo.rbac.authorization.model.Token;

/**
 * @author 戴俊明
 * @version 1.0
 * @className TokenManager
 * @description token的操作类接口
 * @date 2019/5/20 15:26
 **/
public interface TokenManager {
    /**
     * @param username 用户名
     * @return com.demo.rbac.authorization.model.Token
     * @author 戴俊明
     * @description 创建一个token
     * @date 2019/5/20 15:27
     **/
    Token createToken(String username);

    /**
     * @param model 实体
     * @return boolean
     * @author 戴俊明
     * @description 检验该token是否在缓存中
     * @date 2019/5/20 15:30
     **/
    boolean checkToken(Token model);

    /**
     * @param username 用户名
     * @author 戴俊明
     * @description 删除某一token
     * @date 2019/5/20 15:31
     **/
    void deleteToken(String username);

    /**
     * @param authentication 请求头字段的属性
     * @return com.demo.rbac.authorization.model.Token
     * @author 戴俊明
     * @description 将字符串解析成token
     * @date 2019/5/20 15:32
     **/
    Token decode(String authentication);

    /**
     * @param token 实体
     * @return java.lang.String
     * @author 戴俊明
     * @description 将token编码成字符串
     * @date 2019/5/20 15:32
     **/
    String encode(Token token);

}

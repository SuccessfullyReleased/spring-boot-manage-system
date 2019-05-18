package com.demo.rbac.authorization.manager;


import com.demo.rbac.authorization.model.Token;

public interface TokenManager {

    Token createToken(String username);

    boolean checkToken(Token model);

    void deleteToken(String username);

    Token decode(String authentication);

    String encode(Token token);

}

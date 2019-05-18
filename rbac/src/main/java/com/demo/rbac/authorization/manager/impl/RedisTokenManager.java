package com.demo.rbac.authorization.manager.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.demo.rbac.authorization.config.AuthorizationConstants;
import com.demo.rbac.authorization.manager.TokenManager;
import com.demo.rbac.authorization.model.Token;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisTokenManager implements TokenManager {

    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    Gson gson;

    @Override
    public Token createToken(String username) {
        if (StrUtil.isEmpty(username)) {
            return null;
        } else {
            Token token = new Token(username, IdUtil.fastSimpleUUID());
            redisTemplate.opsForValue().set(username, token.getToken(), AuthorizationConstants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
            return token;
        }
    }

    @Override
    public boolean checkToken(Token model) {
        if (model == null) {
            return false;
        } else {
            String redisToken = redisTemplate.opsForValue().get(model.getUsername());
            return model.getToken().equals(redisToken);
        }
    }

    @Override
    public Token decode(String authentication) {
        if (StrUtil.isEmpty(authentication)) {
            return null;
        } else {
//            byte[] decrypt = AuthorizationConstants.rsa.decrypt(
//                    HexUtil.decodeHex(authentication.toCharArray()), KeyType.PrivateKey);
//            return gson.fromJson(StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8), Token.class);
            return gson.fromJson(Base64.decodeStr(authentication), Token.class);
        }
    }

    @Override
    public String encode(Token token) {
        if (token == null) {
            return null;
        } else {
//            byte[] encrypt = AuthorizationConstants.rsa.encrypt(
//                    StrUtil.bytes(gson.toJson(token), CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
//            return new String(HexUtil.encodeHex(encrypt));
            return Base64.encode(gson.toJson(token));
        }
    }

    @Override
    public void deleteToken(String username) {
        if (StrUtil.isEmpty(username)) {
            redisTemplate.delete(username);
        }
    }
}

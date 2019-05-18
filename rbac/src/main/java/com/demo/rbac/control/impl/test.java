package com.demo.rbac.control.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.demo.rbac.authorization.annotation.NoAuthorization;
import com.demo.rbac.authorization.config.AuthorizationConstants;
import com.demo.rbac.authorization.model.Token;
import com.demo.rbac.dao.common.UserDao;
import com.demo.rbac.dao.custom.AccessMapper;
import com.demo.rbac.model.User;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@CrossOrigin
public class test {

    @Autowired
    UserDao userDao;
    @Autowired
    Gson gson;

    //    @GetMapping("users")
//    public List<User> test(@RequestParam HashMap map) {
//        String keyColumn = (String) map.get("keyColumn");
//        String key = (String) map.get("key");
//        String orderColumn = (String) map.get("orderColumn");
//        String order = (String) map.get("order");
//        System.out.println(keyColumn);
//        System.out.println(key);
//        System.out.println(orderColumn);
//        System.out.println(order);
//        return null;
//    }
    @Autowired
    AccessMapper accessMapper;

    @GetMapping("hello/{aid}")
    public String hello(@PathVariable Integer aid) {
        return accessMapper.selectById(aid).toString();
    }

    @PostMapping("test1")
    public String test1(@RequestParam Integer id, @RequestBody User user) {
        System.out.println(id);
        System.out.println(user);
        return null;
    }

    @GetMapping("test2")
    public String test2(@RequestParam HashMap map) {
        String keyColumn = (String) map.get("keyColumn");
        String key = (String) map.get("key");
        String orderColumn = (String) map.get("orderColumn");
        String order = (String) map.get("order");
        System.out.println(keyColumn);
        System.out.println(key);
        System.out.println(orderColumn);
        System.out.println(order);
        return "ok";
    }

    @GetMapping("test3")
    public String test3(@RequestParam User user) {
        System.out.println(user);
        User u = gson.fromJson(user.toString(), User.class);
        System.out.println(u);
        return "user";
    }

    @Autowired
    RedisTemplate<String, String> template;

    @NoAuthorization
    @GetMapping("test4")
    public String test4() {
        String json = gson.toJson(new Token("test1", "test2"));
        log.info(json);

        byte[] encrypt = AuthorizationConstants.rsa.encrypt(
                StrUtil.bytes(json, CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);

        String t = new String(HexUtil.encodeHex(encrypt));
        log.info(t);
        char[] cs = t.toCharArray();
        byte[] _t = HexUtil.decodeHex(cs);
        byte[] decrypt = AuthorizationConstants.rsa.decrypt(
                _t, KeyType.PrivateKey);

        String j = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
        log.info(j);

        return "";
    }

}

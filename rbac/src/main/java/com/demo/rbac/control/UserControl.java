package com.demo.rbac.control;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserControl
 * @description 用户的表现层接口
 * @date 2019/5/20 16:06
 **/
public interface UserControl {
    /**
     * @param uid  用户主键
     * @param file 头像
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 上传（更新）某个用户的头像
     * @date 2019/5/20 16:07
     **/
    ResponseEntity updateAvator(Integer uid, MultipartFile file);

    /**
     * @param username 用户名
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 为某个用户创建一个token
     * @date 2019/5/20 16:08
     **/
    ResponseEntity createToken(String username);

    /**
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 检验token，返回该token的用户名
     * @date 2019/5/20 16:08
     **/
    ResponseEntity checkToken();

}

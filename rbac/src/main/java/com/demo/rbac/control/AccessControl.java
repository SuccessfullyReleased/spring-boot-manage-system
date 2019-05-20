package com.demo.rbac.control;

import org.springframework.http.ResponseEntity;

/**
 * @author 戴俊明
 * @version 1.0
 * @className AccessControl
 * @description 权限的表现层接口
 * @date 2019/5/20 15:47
 **/
public interface AccessControl {
    /**
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 查询所有的权限
     * @date 2019/5/20 15:48
     **/
    ResponseEntity selectAll();

}

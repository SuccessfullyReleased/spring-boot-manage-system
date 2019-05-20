package com.demo.rbac.control;


import com.demo.rbac.model.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className RoleControl
 * @description 角色的表现层接口
 * @date 2019/5/20 16:04
 **/
public interface RoleControl {
    /**
     * @param roles 角色数组
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 查询数组里所有角色的权限（去重）
     * @date 2019/5/20 16:05
     **/
    ResponseEntity selectAccesses(List<Role> roles);

}

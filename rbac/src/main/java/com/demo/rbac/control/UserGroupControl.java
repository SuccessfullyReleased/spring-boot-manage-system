package com.demo.rbac.control;


import com.demo.rbac.model.UserGroup;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserGroupControl
 * @description 用户组的表现层接口
 * @date 2019/5/20 16:11
 **/
public interface UserGroupControl {
    /**
     * @param userGroups 用户组数组
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 查询数组里所有用户组的角色（去重）
     * @date 2019/5/20 16:11
     **/
    ResponseEntity selectRoles(List<UserGroup> userGroups);

}

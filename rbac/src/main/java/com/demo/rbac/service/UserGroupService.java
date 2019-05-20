package com.demo.rbac.service;

import com.demo.rbac.model.Role;
import com.demo.rbac.model.UserGroup;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserGroupService
 * @description 用户组的业务层接口
 * @date 2019/5/20 18:56
 **/
@Validated
public interface UserGroupService {
    /**
     * @param userGroups 用户组数组
     * @return java.util.List<com.demo.rbac.model.Role>
     * @author 戴俊明
     * @description 查询数组里所有用户组的角色（去重）
     * @date 2019/5/20 18:56
     **/
    List<Role> selectRoles(@NotEmpty List<UserGroup> userGroups);

}

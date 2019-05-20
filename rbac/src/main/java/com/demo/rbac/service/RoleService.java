package com.demo.rbac.service;

import com.demo.rbac.model.Access;
import com.demo.rbac.model.Role;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className RoleService
 * @description 角色的业务层接口
 * @date 2019/5/20 18:55
 **/
@Validated
public interface RoleService {
    /**
     * @param roles 角色数组
     * @return java.util.List<com.demo.rbac.model.Access>
     * @author 戴俊明
     * @description 查询数组里所有角色的权限（去重）
     * @date 2019/5/20 18:55
     **/
    List<Access> selectAccesses(@NotEmpty List<Role> roles);

}

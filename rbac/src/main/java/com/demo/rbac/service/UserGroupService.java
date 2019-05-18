package com.demo.rbac.service;

import com.demo.rbac.model.Role;
import com.demo.rbac.model.UserGroup;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
public interface UserGroupService {

    List<Role> selectRoles(@NotEmpty List<UserGroup> userGroups);

}

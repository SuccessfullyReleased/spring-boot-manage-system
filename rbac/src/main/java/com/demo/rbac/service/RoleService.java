package com.demo.rbac.service;

import com.demo.rbac.model.Access;
import com.demo.rbac.model.Role;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
public interface RoleService {

    List<Access> selectAccesses(@NotEmpty List<Role> roles);

}

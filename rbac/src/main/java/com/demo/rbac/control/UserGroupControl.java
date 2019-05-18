package com.demo.rbac.control;


import com.demo.rbac.model.UserGroup;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserGroupControl {

    ResponseEntity selectRoles(List<UserGroup> userGroups);

}

package com.demo.rbac.control;


import com.demo.rbac.model.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleControl {

    ResponseEntity selectAccesses(List<Role> roles);

}

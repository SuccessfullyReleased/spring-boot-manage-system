package com.demo.rbac.dao.common;

import com.demo.rbac.model.Role;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends BaseDao<Role> {
}
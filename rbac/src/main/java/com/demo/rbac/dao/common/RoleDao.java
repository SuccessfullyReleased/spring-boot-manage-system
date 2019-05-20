package com.demo.rbac.dao.common;

import com.demo.rbac.model.Role;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className RoleDao
 * @description 角色的持久层
 * @date 2019/5/20 16:15
 **/
@Repository
public interface RoleDao extends BaseDao<Role> {
}
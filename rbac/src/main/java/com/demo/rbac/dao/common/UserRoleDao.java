package com.demo.rbac.dao.common;

import com.demo.rbac.model.UserRole;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserRoleDao
 * @description 用户与角色关系表的持久层
 * @date 2019/5/20 16:16
 **/
@Repository
public interface UserRoleDao extends BaseDao<UserRole> {
}
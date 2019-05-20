package com.demo.rbac.dao.common;

import com.demo.rbac.model.UserGroupRole;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserGroupRoleDao
 * @description 用户组与角色关系表的持久层
 * @date 2019/5/20 16:16
 **/
@Repository
public interface UserGroupRoleDao extends BaseDao<UserGroupRole> {
}
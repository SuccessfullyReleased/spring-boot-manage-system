package com.demo.rbac.dao.common;

import com.demo.rbac.model.RoleAccess;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className RoleAccessDao
 * @description 角色与权限关系表的持久层
 * @date 2019/5/20 16:15
 **/
@Repository
public interface RoleAccessDao extends BaseDao<RoleAccess> {
}
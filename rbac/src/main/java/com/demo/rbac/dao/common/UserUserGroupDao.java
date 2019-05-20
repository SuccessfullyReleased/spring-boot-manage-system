package com.demo.rbac.dao.common;

import com.demo.rbac.model.UserUserGroup;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserUserGroupDao
 * @description 用户与用户组关系表的持久层
 * @date 2019/5/20 16:17
 **/
@Repository
public interface UserUserGroupDao extends BaseDao<UserUserGroup> {
}
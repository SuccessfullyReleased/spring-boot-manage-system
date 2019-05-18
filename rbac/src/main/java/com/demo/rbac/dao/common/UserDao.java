package com.demo.rbac.dao.common;

import com.demo.rbac.model.User;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseDao<User> {
}
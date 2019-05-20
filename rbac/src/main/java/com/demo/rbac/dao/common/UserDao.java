package com.demo.rbac.dao.common;

import com.demo.rbac.model.User;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserDao
 * @description 用户的持久层
 * @date 2019/5/20 16:15
 **/
@Repository
public interface UserDao extends BaseDao<User> {
}
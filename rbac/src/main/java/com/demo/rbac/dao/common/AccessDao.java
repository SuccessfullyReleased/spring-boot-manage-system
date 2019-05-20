package com.demo.rbac.dao.common;


import com.demo.rbac.model.Access;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className AccessDao
 * @description 权限的持久层
 * @date 2019/5/20 16:12
 **/
@Repository
public interface AccessDao extends BaseDao<Access> {
}
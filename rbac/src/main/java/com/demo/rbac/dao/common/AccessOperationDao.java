package com.demo.rbac.dao.common;

import com.demo.rbac.model.AccessOperation;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className AccessOperationDao
 * @description 权限与操作关系表的持久层
 * @date 2019/5/20 16:13
 **/
@Repository
public interface AccessOperationDao extends BaseDao<AccessOperation> {
}
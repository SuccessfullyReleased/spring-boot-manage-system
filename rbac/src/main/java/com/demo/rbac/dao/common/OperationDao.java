package com.demo.rbac.dao.common;

import com.demo.rbac.model.Operation;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className OperationDao
 * @description 操作的持久层
 * @date 2019/5/20 16:14
 **/
@Repository
public interface OperationDao extends BaseDao<Operation> {
}
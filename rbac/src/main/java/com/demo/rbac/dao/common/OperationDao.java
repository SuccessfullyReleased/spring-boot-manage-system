package com.demo.rbac.dao.common;

import com.demo.rbac.model.Operation;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationDao extends BaseDao<Operation> {
}
package com.demo.rbac.dao.custom;

import com.demo.rbac.model.Access;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className AccessMapper
 * @description 权限的的持久层（复杂sql)
 * @date 2019/5/20 16:17
 **/
@Repository
public interface AccessMapper {
    /**
     * @param aid 权限主键
     * @return com.demo.rbac.model.Access
     * @author 戴俊明
     * @description 通过主键查询某一个权限（关联Menu、Operation表）
     * @date 2019/5/20 16:18
     **/
    Access selectById(@Param("aid") Integer aid);

}

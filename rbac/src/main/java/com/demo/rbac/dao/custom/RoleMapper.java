package com.demo.rbac.dao.custom;

import com.demo.rbac.model.Access;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className RoleMapper
 * @description 角色的持久层（复杂sql)
 * @date 2019/5/20 16:19
 **/
@Repository
public interface RoleMapper {
    /**
     * @param rid 角色主键
     * @return java.util.List<com.demo.rbac.model.Access>
     * @author 戴俊明
     * @description 通过主键查询某一个角色的所有权限
     * @date 2019/5/20 16:20
     **/
    List<Access> selectAccess(@Param("rid") Integer rid);

}

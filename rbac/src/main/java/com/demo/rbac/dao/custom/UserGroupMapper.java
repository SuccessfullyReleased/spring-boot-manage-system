package com.demo.rbac.dao.custom;

import com.demo.rbac.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserGroupMapper
 * @description 用户组的持久层（复杂sql)
 * @date 2019/5/20 16:20
 **/
@Repository
public interface UserGroupMapper {
    /**
     * @param ugid 用户组主键
     * @return java.util.List<com.demo.rbac.model.Role>
     * @author 戴俊明
     * @description 通过主键查询某一个用户组的所有角色
     * @date 2019/5/20 16:21
     **/
    List<Role> selectRole(@Param("ugid") Integer ugid);

}

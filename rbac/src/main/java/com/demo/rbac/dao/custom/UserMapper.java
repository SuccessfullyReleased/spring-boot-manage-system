package com.demo.rbac.dao.custom;

import com.demo.rbac.model.Role;
import com.demo.rbac.model.User;
import com.demo.rbac.model.UserGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserMapper
 * @description 用户的持久层（复杂sql)
 * @date 2019/5/20 16:22
 **/
@Repository
public interface UserMapper {
    /**
     * @param uid 用户主键
     * @return java.util.List<com.demo.rbac.model.UserGroup>
     * @author 戴俊明
     * @description 通过主键查询某一个用户的所有用户组
     * @date 2019/5/20 16:22
     **/
    List<UserGroup> selectUserGroup(@Param("uid") Integer uid);

    /**
     * @param uid 用户主键
     * @return java.util.List<com.demo.rbac.model.Role>
     * @author 戴俊明
     * @description 通过主键查询某一个用户的所有角色
     * @date 2019/5/20 16:22
     **/
    List<Role> selectRole(@Param("uid") Integer uid);

    /**
     * @param groupname   用户组名称
     * @param orderColumn 排序的字段名
     * @param order       排序规则
     * @return java.util.List<com.demo.rbac.model.User>
     * @author 戴俊明
     * @description 模糊查询并排序
     * @date 2019/5/20 16:23
     **/
    List<User> selectGroupName(@Param("groupname") String groupname,
                               @Param("orderColumn") String orderColumn,
                               @Param("order") String order);

    /**
     * @param rolename    角色名称
     * @param orderColumn 排序的字段名
     * @param order       排序规则
     * @return java.util.List<com.demo.rbac.model.User>
     * @author 戴俊明
     * @description 模糊查询并排序
     * @date 2019/5/20 16:24
     **/
    List<User> selectRoleName(@Param("rolename") String rolename,
                              @Param("orderColumn") String orderColumn,
                              @Param("order") String order);

}

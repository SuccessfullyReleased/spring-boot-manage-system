package com.demo.rbac.dao.custom;

import com.demo.rbac.model.Role;
import com.demo.rbac.model.User;
import com.demo.rbac.model.UserGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<UserGroup> selectUserGroup(@Param("uid") Integer uid);

    List<Role> selectRole(@Param("uid") Integer uid);

    List<User> selectGroupName(@Param("groupname") String groupname,
                               @Param("orderColumn") String orderColumn,
                               @Param("order") String order);

    List<User> selectRoleName(@Param("rolename") String rolename,
                              @Param("orderColumn") String orderColumn,
                              @Param("order") String order);

}

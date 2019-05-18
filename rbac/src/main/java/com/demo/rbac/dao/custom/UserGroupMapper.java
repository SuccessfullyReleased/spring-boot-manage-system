package com.demo.rbac.dao.custom;

import com.demo.rbac.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupMapper {

    List<Role> selectRole(@Param("ugid") Integer ugid);

}

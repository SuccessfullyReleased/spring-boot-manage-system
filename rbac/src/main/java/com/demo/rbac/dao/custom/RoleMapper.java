package com.demo.rbac.dao.custom;

import com.demo.rbac.model.Access;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    List<Access> selectAccess(@Param("rid") Integer rid);

}

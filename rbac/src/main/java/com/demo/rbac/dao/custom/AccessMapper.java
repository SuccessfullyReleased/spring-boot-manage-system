package com.demo.rbac.dao.custom;

import com.demo.rbac.model.Access;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessMapper {

    Access selectById(@Param("aid") Integer aid);

}

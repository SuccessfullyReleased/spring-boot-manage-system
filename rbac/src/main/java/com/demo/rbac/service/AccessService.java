package com.demo.rbac.service;


import com.demo.rbac.model.Access;

import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className AccessService
 * @description 权限的业务层接口
 * @date 2019/5/20 18:47
 **/
public interface AccessService {
    /**
     * @return java.util.List<com.demo.rbac.model.Access>
     * @author 戴俊明
     * @description 查询所有的权限
     * @date 2019/5/20 18:52
     **/
    List<Access> selectAll();

}

package com.demo.rbac.dao.common;


import com.demo.rbac.model.AccessMenu;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className AccessMenuDao
 * @description 权限与菜单关系表的持久层
 * @date 2019/5/20 16:13
 **/
@Repository
public interface AccessMenuDao extends BaseDao<AccessMenu> {
}
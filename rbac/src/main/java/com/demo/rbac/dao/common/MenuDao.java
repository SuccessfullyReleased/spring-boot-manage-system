package com.demo.rbac.dao.common;

import com.demo.rbac.model.Menu;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @version 1.0
 * @className MenuDao
 * @description 菜单的持久层
 * @date 2019/5/20 16:14
 **/
@Repository
public interface MenuDao extends BaseDao<Menu> {
}
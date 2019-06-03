package com.demo.cms.dao;

import com.demo.cms.model.Material;
import com.demo.cms.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @className MaterialDao
 * @description 材料的通用mapper类
 * @date 2019/5/27 10:53
 **/

@Repository
public interface MaterialDao extends BaseDao<Material> {
}

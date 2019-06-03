package com.demo.cms.util.dao;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.select.SelectCountMapper;

/**
 * @author 戴俊明
 * @version 1.0
 * @className BaseDao
 * @description 通用Mapper的基础接口类
 * @date 2019/5/20 18:57
 **/
public interface BaseDao<T> extends Mapper<T>, SelectCountMapper<T>, MySqlMapper<T> {
}

package com.demo.rbac.util.dao;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.select.SelectCountMapper;

public interface BaseDao<T> extends Mapper<T>, SelectCountMapper<T> {
}

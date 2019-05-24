package com.demo.cms.control;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className CommonControl
 * @description 表现层的基本CRUD接口
 * @date 2019/5/20 16:03
 **/
public interface CommonControl<T> {
    /**
     * @param json json数据
     * @return T
     * @author 戴俊明
     * @description 将获取的json数据解析成对应的实体类
     * @date 2019/5/20 15:54
     **/
    T getModel(String json);

    /**
     * @param id 主键
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 通过主键查询
     * @date 2019/5/20 15:55
     **/
    ResponseEntity selectOneById(Integer id);

    /**
     * @param model 实体
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 通过实体类的属性精确查询
     * @date 2019/5/20 15:56
     **/
    ResponseEntity selectOne(String model);

    /**
     * @param model 实体
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 通过实体类的属性精确查询符合条件的记录数量
     * @date 2019/5/20 15:56
     **/
    ResponseEntity selectCount(String model);

    /**
     * @param keyColumn   关键字的字段名
     * @param key         关键字
     * @param orderColumn 排序的字段名
     * @param order       排序规则
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 模糊查询并排序
     * @date 2019/5/20 15:57
     **/
    ResponseEntity selectRecords(String keyColumn, String key, String orderColumn, String order);

    /**
     * @param model 实体
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 插入一条记录
     * @date 2019/5/20 15:59
     **/
    ResponseEntity insertRecord(T model);

    /**
     * @param id 主键
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 根据主键删除一条记录
     * @date 2019/5/20 16:00
     **/
    ResponseEntity deleteRecord(Integer id);

    /**
     * @param list 主键的数组
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 根据主键数组删除多条记录
     * @date 2019/5/20 16:01
     **/
    ResponseEntity deleteRecords(List<T> list);

    /**
     * @param model 实体
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 更新一条记录
     * @date 2019/5/20 16:01
     **/
    ResponseEntity updateRecord(T model);
}

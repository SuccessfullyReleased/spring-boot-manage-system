package com.demo.rbac.control;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommonControl<T> {

    T getModel(String json);

    ResponseEntity selectOneById(Integer id);

    ResponseEntity selectOne(String model);

    ResponseEntity selectCount(String model);

    ResponseEntity selectRecords(String keyColumn, String key, String orderColumn, String order);

    ResponseEntity insertRecord(T model);

    ResponseEntity deleteRecord(Integer id);

    ResponseEntity deleteRecords(List<T> list);

    ResponseEntity updateRecord(T model);
}

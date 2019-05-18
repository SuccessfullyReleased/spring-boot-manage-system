package com.demo.rbac.control;

import com.demo.rbac.service.BaseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseControl<T> implements CommonControl<T> {

    private BaseService<T> service;

    public void setService(BaseService<T> service) {
        this.service = service;
    }

    public BaseService<T> getService() {
        return service;
    }

    @Autowired
    private Gson gson;

    @Override
    public T getModel(String json) {
        Class<T> TClass = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        return gson.fromJson(json, TClass);
    }

    @Override
    public ResponseEntity selectOneById(Integer id) {
        return new ResponseEntity<>(service.selectOneById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity selectOne(String model) {
        return new ResponseEntity<>(service.selectOne(getModel(model)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity selectCount(String model) {
        return new ResponseEntity<>(service.selectCount(getModel(model)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity selectRecords(String keyColumn, String key, String orderColumn, String order) {
        return new ResponseEntity<>(service.selectRecords(keyColumn, key, orderColumn, order), HttpStatus.OK);
    }

    @Override
    public ResponseEntity insertRecord(T model) {
        return new ResponseEntity<>(service.insertRecord(model), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity deleteRecord(Integer id) {
        return new ResponseEntity<>(service.deleteRecord(id), HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity deleteRecords(List<T> list) {
        return new ResponseEntity<>(service.deleteRecords(list), HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity updateRecord(T model) {
        return new ResponseEntity<>(service.updateRecord(model), HttpStatus.OK);
    }
}

package com.demo.cms.control;

import com.demo.cms.service.BaseService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className BaseControl
 * @description 封装了基本的CRUD表现
 * @date 2019/5/20 15:48
 **/
public abstract class BaseControl<T> implements CommonControl<T> {
    /**
     * @author 戴俊明
     * @description 业务类
     * @date 2019/5/20 15:53
     **/
    private BaseService<T> service;

    /**
     * @param service 业务类实体
     * @author 戴俊明
     * @description 获取业务类（必须重写）
     * @date 2019/5/20 15:49
     **/
    public void setService(BaseService<T> service) {
        this.service = service;
    }

    /**
     * @return com.demo.rbac.service.BaseService<T>
     * @author 戴俊明
     * @description 获取业务类，以支持各个表现层独有的业务
     * @date 2019/5/20 15:51
     **/
    public BaseService<T> getService() {
        return service;
    }

    /**
     * @author 戴俊明
     * @description json解析类
     * @date 2019/5/20 15:54
     **/
    @Autowired
    private Gson gson;

    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

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
    public ResponseEntity select(String model) {
        return new ResponseEntity<>(service.select(getModel(model)), HttpStatus.OK);
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

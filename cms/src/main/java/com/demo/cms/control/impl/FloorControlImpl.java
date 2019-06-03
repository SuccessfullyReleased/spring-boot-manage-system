package com.demo.cms.control.impl;

import com.demo.cms.control.BaseControl;
import com.demo.cms.model.Floor;
import com.demo.cms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 戴俊明
 * @className FloorControlImpl
 * @description 楼层的表现层
 * @date 2019/5/24 13:49
 **/

@Slf4j
@RestController
public class FloorControlImpl extends BaseControl<Floor> {

    @Autowired
    @Override
    public void setService(BaseService<Floor> service) {
        super.setService(service);
    }

    @GetMapping(value = "floor/fid/{id}")
    @Override
    public ResponseEntity selectOneById(@PathVariable Integer id) {
        log.info("FloorControlImpl::selectOneById::id = [{}]", id);
        return super.selectOneById(id);
    }

    @GetMapping(value = "floor")
    @Override
    public ResponseEntity selectOne(@RequestParam String model) {
        log.info("FloorControlImpl::selectOne::model = [{}]", model);
        return super.selectOne(model);
    }

    @GetMapping(value = "floors")
    @Override
    public ResponseEntity select(@RequestParam String model) {
        log.info("FloorControlImpl::select::model = [{}]", model);
        return super.select(model);
    }

    @GetMapping(value = "floor/count")
    @Override
    public ResponseEntity selectCount(@RequestParam String model) {
        log.info("FloorControlImpl::selectCount::model = [{}]", model);
        return super.selectCount(model);
    }

    @PostMapping(value = "floor")
    @Override
    public ResponseEntity insertRecord(@RequestBody Floor model) {
        log.info("FloorControlImpl::insertRecord::model = [{}]", model);
        return super.insertRecord(model);
    }

    @DeleteMapping(value = "floor/fid/{id}")
    @Override
    public ResponseEntity deleteRecord(@PathVariable Integer id) {
        log.info("FloorControlImpl::deleteRecord::id = [{}]", id);
        return super.deleteRecord(id);
    }

    @DeleteMapping(value = "floors")
    @Override
    public ResponseEntity deleteRecords(@RequestBody List<Floor> list) {
        log.info("FloorControlImpl::deleteRecords::list = [{}]", list);
        return super.deleteRecords(list);
    }

    @PutMapping(value = "floor")
    @Override
    public ResponseEntity updateRecord(@RequestBody Floor model) {
        log.info("FloorControlImpl::updateRecord::model = [{}]", model);
        return super.updateRecord(model);
    }
}

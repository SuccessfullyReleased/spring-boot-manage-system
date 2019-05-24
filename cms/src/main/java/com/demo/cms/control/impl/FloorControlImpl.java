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
        return super.selectOneById(id);
    }

    @GetMapping(value = "floor")
    @Override
    public ResponseEntity selectOne(@RequestParam String model) {
        return super.selectOne(model);
    }

    @GetMapping(value = "floor/count")
    @Override
    public ResponseEntity selectCount(@RequestParam String model) {
        return super.selectCount(model);
    }

    @GetMapping(value = "floors")
    @Override
    public ResponseEntity selectRecords(@RequestParam String keyColumn,
                                        @RequestParam String key,
                                        @RequestParam String orderColumn,
                                        @RequestParam String order) {
        return super.selectRecords(keyColumn, key, orderColumn, order);
    }

    @PostMapping(value = "floor")
    @Override
    public ResponseEntity insertRecord(@RequestBody Floor model) {
        return super.insertRecord(model);
    }

    @DeleteMapping(value = "floor/fid/{id}")
    @Override
    public ResponseEntity deleteRecord(@PathVariable Integer id) {
        return super.deleteRecord(id);
    }

    @DeleteMapping(value = "floors")
    @Override
    public ResponseEntity deleteRecords(@RequestBody List<Floor> list) {
        return super.deleteRecords(list);
    }

    @PutMapping(value = "floor")
    @Override
    public ResponseEntity updateRecord(@RequestBody Floor model) {
        return super.updateRecord(model);
    }
}

package com.demo.cms.control.impl;

import com.demo.cms.control.BaseControl;
import com.demo.cms.model.Note;
import com.demo.cms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 戴俊明
 * @className NoteControlImpl
 * @description 帖子的表现层
 * @date 2019/5/24 13:42
 **/

@Slf4j
@RestController
public class NoteControlImpl extends BaseControl<Note> {

    @Autowired
    @Override
    public void setService(BaseService<Note> service) {
        super.setService(service);
    }

    @GetMapping(value = "note/nid/{id}")
    @Override
    public ResponseEntity selectOneById(@PathVariable Integer id) {
        log.info("NoteControlImpl::selectOneById::id = [{}]", id);
        return super.selectOneById(id);
    }

    @GetMapping(value = "note")
    @Override
    public ResponseEntity selectOne(@RequestParam String model) {
        log.info("NoteControlImpl::selectOne::model = [{}]", model);
        return super.selectOne(model);
    }

    @GetMapping(value = "note/count")
    @Override
    public ResponseEntity selectCount(@RequestParam String model) {
        log.info("NoteControlImpl::selectCount::model = [{}]", model);
        return super.selectCount(model);
    }

    @GetMapping(value = "notes")
    @Override
    public ResponseEntity selectRecords(@RequestParam(required = false) String keyColumn,
                                        @RequestParam(required = false) String key,
                                        @RequestParam(required = false) String orderColumn,
                                        @RequestParam(required = false) String order) {
        log.info("NoteControlImpl::selectRecords::keyColumn = [{}], key = [{}], orderColumn = [{}], order = [{}]", keyColumn, key, orderColumn, order);
        return super.selectRecords(keyColumn, key, orderColumn, order);
    }

    @PostMapping(value = "note")
    @Override
    public ResponseEntity insertRecord(@RequestBody Note model) {
        log.info("NoteControlImpl::insertRecord::model = [{}]", model);
        return super.insertRecord(model);
    }

    @DeleteMapping(value = "note/nid/{id}")
    @Override
    public ResponseEntity deleteRecord(@PathVariable Integer id) {
        log.info("NoteControlImpl::deleteRecord::id = [{}]", id);
        return super.deleteRecord(id);
    }

    @DeleteMapping(value = "notes")
    @Override
    public ResponseEntity deleteRecords(@RequestBody List<Note> list) {
        log.info("NoteControlImpl::deleteRecords::list = [{}]", list);
        return super.deleteRecords(list);
    }

    @PutMapping(value = "note")
    @Override
    public ResponseEntity updateRecord(@RequestBody Note model) {
        log.info("NoteControlImpl::updateRecord::model = [{}]", model);
        return super.updateRecord(model);
    }
}

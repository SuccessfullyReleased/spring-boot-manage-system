package com.demo.cms.control.impl;

import com.demo.cms.control.BaseControl;
import com.demo.cms.model.Comment;
import com.demo.cms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 戴俊明
 * @className CommentControlImpl
 * @description 评论的表现层
 * @date 2019/5/24 13:58
 **/

@Slf4j
@RestController
public class CommentControlImpl extends BaseControl<Comment> {

    @Autowired
    @Override
    public void setService(BaseService<Comment> service) {
        super.setService(service);
    }

    @GetMapping(value = "comment/cid/{id}")
    @Override
    public ResponseEntity selectOneById(@PathVariable Integer id) {
        log.info("CommentControlImpl::selectOneById::id = [{}]", id);
        return super.selectOneById(id);
    }

    @GetMapping(value = "comment")
    @Override
    public ResponseEntity selectOne(@RequestParam String model) {
        log.info("CommentControlImpl::selectOne::model = [{}]", model);
        return super.selectOne(model);
    }

    @GetMapping(value = "comments")
    @Override
    public ResponseEntity select(@RequestParam String model) {
        log.info("CommentControlImpl::select::model = [{}]", model);
        return super.select(model);
    }

    @GetMapping(value = "comment/count")
    @Override
    public ResponseEntity selectCount(@RequestParam String model) {
        log.info("CommentControlImpl::selectCount::model = [{}]", model);
        return super.selectCount(model);
    }

    @PostMapping(value = "comment")
    @Override
    public ResponseEntity insertRecord(@RequestBody Comment model) {
        log.info("CommentControlImpl::insertRecord::model = [{}]", model);
        return super.insertRecord(model);
    }

    @DeleteMapping(value = "comment/cid/{id}")
    @Override
    public ResponseEntity deleteRecord(@PathVariable Integer id) {
        log.info("CommentControlImpl::deleteRecord::id = [{}]", id);
        return super.deleteRecord(id);
    }

    @DeleteMapping(value = "comments")
    @Override
    public ResponseEntity deleteRecords(@RequestBody List<Comment> list) {
        log.info("CommentControlImpl::deleteRecords::list = [{}]", list);
        return super.deleteRecords(list);
    }

    @PutMapping(value = "comment")
    @Override
    public ResponseEntity updateRecord(@RequestBody Comment model) {
        log.info("CommentControlImpl::updateRecord::model = [{}]", model);
        return super.updateRecord(model);
    }
}

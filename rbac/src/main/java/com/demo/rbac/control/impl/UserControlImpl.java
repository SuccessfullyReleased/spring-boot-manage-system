package com.demo.rbac.control.impl;

import com.demo.rbac.authorization.annotation.NoAuthorization;
import com.demo.rbac.authorization.manager.TokenManager;
import com.demo.rbac.control.BaseControl;
import com.demo.rbac.control.UserControl;
import com.demo.rbac.model.User;
import com.demo.rbac.service.BaseService;
import com.demo.rbac.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
public class UserControlImpl extends BaseControl<User> implements UserControl {

    @Autowired
    @Override
    public void setService(BaseService<User> service) {
        super.setService(service);
    }

    @Autowired
    TokenManager manager;

    @PostMapping(value = "user/avator")
    @Override
    public ResponseEntity updateAvator(@RequestParam Integer uid, @RequestParam MultipartFile file) {
        log.info("UserControlImpl::updateAvator::uid = [{}], file = [{}]", uid, file);
        UserService service = (UserService) getService();
        service.updateAvator(uid, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @NoAuthorization
    @PostMapping(value = "user/token")
    @Override
    public ResponseEntity createToken(@RequestParam String username) {
        log.info("UserControlImpl::createToken::username = [{}]", username);
        return new ResponseEntity<>(manager.encode(manager.createToken(username)), HttpStatus.OK);
    }

    @GetMapping(value = "user/token")
    @Override
    public ResponseEntity checkToken() {
        log.info("UserControlImpl::checkToken::");
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "user/uid/{id}")
    @Override
    public ResponseEntity selectOneById(@PathVariable Integer id) {
        log.info("UserControlImpl::selectOneById::id = [{}]", id);
        return super.selectOneById(id);
    }

    @GetMapping(value = "user")
    @Override
    public ResponseEntity selectOne(@RequestParam String model) {
        log.info("UserControlImpl::selectOne::model = [{}]", model);
        return super.selectOne(model);
    }

    @NoAuthorization
    @GetMapping(value = "user/count")
    @Override
    public ResponseEntity selectCount(@RequestParam String model) {
        log.info("UserControlImpl::selectCount::model = [{}]", model);
        return super.selectCount(model);
    }

    @GetMapping(value = "users")
    @Override
    public ResponseEntity selectRecords(@RequestParam(required = false) String keyColumn,
                                        @RequestParam(required = false) String key,
                                        @RequestParam(required = false) String orderColumn,
                                        @RequestParam(required = false) String order) {
        log.info("UserControlImpl::selectRecords::keyColumn = [{}], key = [{}], orderColumn = [{}], order = [{}]", keyColumn, key, orderColumn, order);
        return super.selectRecords(keyColumn, key, orderColumn, order);
    }

    @PostMapping(value = "user")
    @Override
    public ResponseEntity insertRecord(@RequestBody User model) {
        log.info("UserControlImpl::insertRecord::model = [{}]", model);
        return super.insertRecord(model);
    }

    @DeleteMapping(value = "user/uid/{id}")
    @Override
    public ResponseEntity deleteRecord(@PathVariable Integer id) {
        log.info("UserControlImpl::deleteRecord::id = [{}]", id);
        return super.deleteRecord(id);
    }

    @DeleteMapping(value = "users")
    @Override
    public ResponseEntity deleteRecords(@RequestBody List<User> list) {
        log.info("UserControlImpl::deleteRecords::list = [{}]", list);
        return super.deleteRecords(list);
    }

    @PutMapping(value = "user")
    @Override
    public ResponseEntity updateRecord(@RequestBody User model) {
        log.info("UserControlImpl::updateRecord::model = [{}]", model);
        return super.updateRecord(model);
    }
}

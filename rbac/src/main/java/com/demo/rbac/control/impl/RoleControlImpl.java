package com.demo.rbac.control.impl;


import com.demo.rbac.authorization.annotation.NoAuthorization;
import com.demo.rbac.control.BaseControl;
import com.demo.rbac.control.RoleControl;
import com.demo.rbac.model.Role;
import com.demo.rbac.service.BaseService;
import com.demo.rbac.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RoleControlImpl extends BaseControl<Role> implements RoleControl {

    @Autowired
    @Override
    public void setService(BaseService<Role> service) {
        super.setService(service);
    }

    @NoAuthorization
    @GetMapping(value = "role/rid/{id}")
    @Override
    public ResponseEntity selectOneById(@PathVariable Integer id) {
        log.info("RoleControlImpl::selectOneById::id = [{}]", id);
        return super.selectOneById(id);
    }

    @NoAuthorization
    @GetMapping(value = "role")
    @Override
    public ResponseEntity selectOne(@RequestParam String model) {
        log.info("RoleControlImpl::selectOne::model = [{}]", model);
        return super.selectOne(model);
    }

    @NoAuthorization
    @GetMapping(value = "role/count")
    @Override
    public ResponseEntity selectCount(@RequestParam String model) {
        log.info("RoleControlImpl::selectCount::model = [{}]", model);
        return super.selectCount(model);
    }

    @NoAuthorization
    @GetMapping(value = "roles")
    @Override
    public ResponseEntity selectRecords(@RequestParam(required = false) String keyColumn,
                                        @RequestParam(required = false) String key,
                                        @RequestParam(required = false) String orderColumn,
                                        @RequestParam(required = false) String order) {
        log.info("RoleControlImpl::selectRecords::keyColumn = [{}], key = [{}], orderColumn = [{}], order = [{}]", keyColumn, key, orderColumn, order);
        return super.selectRecords(keyColumn, key, orderColumn, order);
    }

    @PostMapping(value = "role")
    @Override
    public ResponseEntity insertRecord(@RequestBody Role model) {
        log.info("RoleControlImpl::insertRecord::model = [{}]", model);
        return super.insertRecord(model);
    }

    @DeleteMapping(value = "role/rid/{id}")
    @Override
    public ResponseEntity deleteRecord(@PathVariable Integer id) {
        log.info("RoleControlImpl::deleteRecord::id = [{}]", id);
        return super.deleteRecord(id);
    }

    @DeleteMapping(value = "roles")
    @Override
    public ResponseEntity deleteRecords(@RequestBody List<Role> list) {
        log.info("RoleControlImpl::deleteRecords::list = [{}]", list);
        return super.deleteRecords(list);
    }

    @PutMapping(value = "role")
    @Override
    public ResponseEntity updateRecord(@RequestBody Role model) {
        log.info("RoleControlImpl::updateRecord::model = [{}]", model);
        return super.updateRecord(model);
    }

    @PostMapping(value = "roles/accesses")
    @Override
    public ResponseEntity selectAccesses(@RequestBody List<Role> roles) {
        log.info("RoleControlImpl::selectAccesses::roles = [{}]", roles);
        RoleService service = (RoleService) getService();
        return new ResponseEntity<>(service.selectAccesses(roles), HttpStatus.OK);
    }
}

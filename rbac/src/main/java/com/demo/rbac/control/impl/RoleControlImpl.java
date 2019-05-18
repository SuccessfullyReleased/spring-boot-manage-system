package com.demo.rbac.control.impl;


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

    @GetMapping(value = "role/rid/{id}")
    @Override
    public ResponseEntity selectOneById(@PathVariable Integer id) {
        return super.selectOneById(id);
    }

    @GetMapping(value = "role")
    @Override
    public ResponseEntity selectOne(@RequestParam String model) {
        return super.selectOne(model);
    }

    @GetMapping(value = "role/count")
    @Override
    public ResponseEntity selectCount(@RequestParam String model) {
        return super.selectCount(model);
    }

    @GetMapping(value = "roles")
    @Override
    public ResponseEntity selectRecords(@RequestParam(required = false) String keyColumn,
                                        @RequestParam(required = false) String key,
                                        @RequestParam(required = false) String orderColumn,
                                        @RequestParam(required = false) String order) {
        return super.selectRecords(keyColumn, key, orderColumn, order);
    }

    @PostMapping(value = "role")
    @Override
    public ResponseEntity insertRecord(@RequestBody Role model) {
        return super.insertRecord(model);
    }

    @DeleteMapping(value = "role/rid/{id}")
    @Override
    public ResponseEntity deleteRecord(@PathVariable Integer id) {
        return super.deleteRecord(id);
    }

    @DeleteMapping(value = "roles")
    @Override
    public ResponseEntity deleteRecords(@RequestBody List<Role> list) {
        return super.deleteRecords(list);
    }

    @PutMapping(value = "role")
    @Override
    public ResponseEntity updateRecord(@RequestBody Role model) {
        return super.updateRecord(model);
    }

    @PostMapping(value = "roles/accesses")
    @Override
    public ResponseEntity selectAccesses(@RequestBody List<Role> roles) {
        RoleService service = (RoleService) getService();
        return new ResponseEntity<>(service.selectAccesses(roles), HttpStatus.OK);
    }
}

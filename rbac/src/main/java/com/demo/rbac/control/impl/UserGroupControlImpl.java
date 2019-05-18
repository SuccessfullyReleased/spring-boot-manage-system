package com.demo.rbac.control.impl;

import com.demo.rbac.control.BaseControl;
import com.demo.rbac.control.UserGroupControl;
import com.demo.rbac.model.UserGroup;
import com.demo.rbac.service.BaseService;
import com.demo.rbac.service.UserGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserGroupControlImpl extends BaseControl<UserGroup> implements UserGroupControl {

    @Autowired
    @Override
    public void setService(BaseService<UserGroup> service) {
        super.setService(service);
    }

    @GetMapping(value = "userGroup/ugid/{id}")
    @Override
    public ResponseEntity selectOneById(@PathVariable Integer id) {
        return super.selectOneById(id);
    }

    @GetMapping(value = "userGroup")
    @Override
    public ResponseEntity selectOne(@RequestParam String model) {
        return super.selectOne(model);
    }

    @GetMapping(value = "userGroup/count")
    @Override
    public ResponseEntity selectCount(@RequestParam String model) {
        return super.selectCount(model);
    }

    @GetMapping(value = "userGroups")
    @Override
    public ResponseEntity selectRecords(@RequestParam(required = false) String keyColumn,
                                        @RequestParam(required = false) String key,
                                        @RequestParam(required = false) String orderColumn,
                                        @RequestParam(required = false) String order) {
        return super.selectRecords(keyColumn, key, orderColumn, order);
    }

    @PostMapping(value = "userGroup")
    @Override
    public ResponseEntity insertRecord(@RequestBody UserGroup model) {
        return super.insertRecord(model);
    }

    @DeleteMapping(value = "userGroup/ugid/{id}")
    @Override
    public ResponseEntity deleteRecord(@PathVariable Integer id) {
        return super.deleteRecord(id);
    }

    @DeleteMapping(value = "userGroups")
    @Override
    public ResponseEntity deleteRecords(@RequestBody List<UserGroup> list) {
        return super.deleteRecords(list);
    }

    @PutMapping(value = "userGroup")
    @Override
    public ResponseEntity updateRecord(@RequestBody UserGroup model) {
        return super.updateRecord(model);
    }

    @PostMapping(value = "userGroups/roles")
    @Override
    public ResponseEntity selectRoles(@RequestBody List<UserGroup> userGroups) {
        UserGroupService service = (UserGroupService) getService();
        return new ResponseEntity<>(service.selectRoles(userGroups), HttpStatus.OK);
    }
}

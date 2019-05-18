package com.demo.rbac.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.demo.rbac.dao.common.AccessDao;
import com.demo.rbac.dao.common.RoleAccessDao;
import com.demo.rbac.dao.common.UserDao;
import com.demo.rbac.dao.common.UserRoleDao;
import com.demo.rbac.dao.custom.AccessMapper;
import com.demo.rbac.dao.custom.RoleMapper;
import com.demo.rbac.model.*;
import com.demo.rbac.service.BaseService;
import com.demo.rbac.service.RoleService;
import com.demo.rbac.util.dao.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    @Override
    public void setDao(BaseDao<Role> dao) {
        super.setDao(dao);
    }

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    AccessDao accessDao;
    @Autowired
    RoleAccessDao roleAccessDao;
    @Autowired
    AccessMapper accessMapper;
    @Autowired
    UserDao userDao;
    @Autowired
    UserRoleDao userRoleDao;

    @Cacheable(value = "roles", key = "#id")
    @Override
    public Role selectOneById(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id) {
        log.info("RoleServiceImpl: " + "selectOneById: " + id);
        Role role = super.selectOneById(id);
        role.setAccesses(roleMapper.selectAccess(role.getRid())
                .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        if (CollUtil.isNotEmpty(role.getAccesses())) {
            for (Access access : role.getAccesses()) {
                access = accessMapper.selectById(access.getAid());
            }
        }
        return role;
    }

    @Cacheable(value = "roles", key = "#model")
    @Override
    public Role selectOne(@NotNull Role model) {
        log.info("RoleServiceImpl: " + "selectOne: " + model);
        Role role = super.selectOne(model);
        role.setAccesses(roleMapper.selectAccess(role.getRid())
                .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        return role;
    }

    @Override
    public int insertRecord(@NotNull Role model) {
        log.info("RoleServiceImpl: " + "insertRecord: " + model);
        User admin = new User();
        admin.setUsername("admin");
        admin = userDao.selectOne(admin);
        int result = super.insertRecord(model);
        userRoleDao.insertSelective(new UserRole(null, admin.getUid(), model.getRid()));
        return result;
    }

    @Override
    public List<Role> selectRecords(String keyColumn, String key, String orderColumn, String order) {
        List<Role> roles = super.selectRecords(keyColumn, key, orderColumn, order);
        for (Role role : roles) {
            role.setAccesses(roleMapper.selectAccess(role.getRid())
                    .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }
        return roles;
    }

    @CacheEvict(value = "roles", allEntries = true)
    @Override
    public int deleteRecords(@NotEmpty List<Role> list) {
        return list.stream().mapToInt(Role::getRid).reduce(0, (x, y) -> x + getDao().deleteByPrimaryKey(y));
    }

    @CacheEvict(value = "roles", allEntries = true)
    @Override
    public int updateRecord(@NotNull Role model) {
        if (CollUtil.isNotEmpty(model.getAccesses())) {
            List<Integer> news = model.getAccesses().stream().mapToInt(Access::getAid).boxed().distinct().collect(Collectors.toList());
            List<Integer> olds = roleMapper.selectAccess(model.getRid())
                    .stream().filter(Objects::nonNull).mapToInt(Access::getAid).boxed().distinct().collect(Collectors.toList());
            news.removeAll(olds);
            olds.removeAll(model.getAccesses().stream().mapToInt(Access::getAid).boxed().distinct().collect(Collectors.toList()));
            for (Integer aid : news) {
                roleAccessDao.insertSelective(new RoleAccess(null, model.getRid(), aid));
            }
            for (Integer aid : olds) {
                roleAccessDao.delete(new RoleAccess(null, model.getRid(), aid));
            }
        }
        if (model.getRolename() != null || model.getNote() != null || model.getParent() != null) {
            return super.updateRecord(model);
        } else {
            return 0;
        }
    }

    @Override
    public List<Access> selectAccesses(@NotEmpty List<Role> roles) {
        List<Access> accesses = new ArrayList<>();
        for (Role role : roles) {
            accesses.addAll(roleMapper.selectAccess(role.getRid())
                    .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }
        for (Access access : accesses) {
            access.setMenu(accessMapper.selectById(access.getAid()).getMenu());
            access.setOperation(accessMapper.selectById(access.getAid()).getOperation());
        }
        return accesses;
    }
}

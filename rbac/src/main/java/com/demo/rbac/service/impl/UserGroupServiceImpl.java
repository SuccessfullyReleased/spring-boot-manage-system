package com.demo.rbac.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.demo.rbac.dao.common.RoleDao;
import com.demo.rbac.dao.common.UserDao;
import com.demo.rbac.dao.common.UserGroupRoleDao;
import com.demo.rbac.dao.common.UserUserGroupDao;
import com.demo.rbac.dao.custom.UserGroupMapper;
import com.demo.rbac.model.*;
import com.demo.rbac.service.BaseService;
import com.demo.rbac.service.UserGroupService;
import com.demo.rbac.util.Exception.BaseException;
import com.demo.rbac.util.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserGroupServiceImpl extends BaseService<UserGroup> implements UserGroupService {

    @Autowired
    @Override
    public void setDao(BaseDao<UserGroup> dao) {
        super.setDao(dao);
    }

    @Autowired
    UserGroupMapper userGroupMapper;
    @Autowired
    UserGroupRoleDao userGroupRoleDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserDao userDao;
    @Autowired
    UserUserGroupDao userUserGroupDao;

    @Cacheable(value = "groups", key = "#id")
    @Override
    public UserGroup selectOneById(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id) {
        UserGroup userGroup = super.selectOneById(id);
        userGroup.setRoles(userGroupMapper.selectRole(userGroup.getUgid())
                .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        return userGroup;
    }

    @Cacheable(value = "groups", key = "#model")
    @Override
    public UserGroup selectOne(@NotNull UserGroup model) {
        UserGroup userGroup = super.selectOne(model);
        userGroup.setRoles(userGroupMapper.selectRole(userGroup.getUgid())
                .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        return userGroup;
    }

    @Override
    public int insertRecord(@NotNull UserGroup model) {
        User admin = new User();
        admin.setUsername("admin");
        admin = userDao.selectOne(admin);
        int result = super.insertRecord(model);
        userUserGroupDao.insertSelective(new UserUserGroup(null, admin.getUid(), model.getUgid()));
        if (CollUtil.isNotEmpty(model.getRoles())) {
            for (Role role : model.getRoles()) {
                if (roleDao.selectCount(role) > 0) {
                    userGroupRoleDao.insertSelective(new UserGroupRole(null, model.getUgid(), role.getRid()));
                } else {
                    throw new BaseException("找不到符合" + role.toString() + "的记录", HttpStatus.BAD_REQUEST);
                }
            }
        }
        return result;
    }

    @Override
    public List<UserGroup> selectRecords(String keyColumn, String key, String orderColumn, String order) {
        List<UserGroup> userGroups = super.selectRecords(keyColumn, key, orderColumn, order);
        for (UserGroup userGroup : userGroups) {
            userGroup.setRoles(userGroupMapper.selectRole(userGroup.getUgid())
                    .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }
        return userGroups;
    }

    @CacheEvict(value = "groups", allEntries = true)
    @Override
    public int deleteRecords(@NotEmpty List<UserGroup> list) {
        return list.stream().mapToInt(UserGroup::getUgid).reduce(0, (x, y) -> x + getDao().deleteByPrimaryKey(y));
    }

    @CacheEvict(value = "groups", allEntries = true)
    @Override
    public int updateRecord(@NotNull UserGroup model) {
        if (CollUtil.isNotEmpty(model.getRoles())) {
            List<Integer> news = model.getRoles().stream().mapToInt(Role::getRid).boxed().distinct().collect(Collectors.toList());
            List<Integer> olds = userGroupMapper.selectRole(model.getUgid())
                    .stream().filter(Objects::nonNull).mapToInt(Role::getRid).boxed().distinct().collect(Collectors.toList());
            news.removeAll(olds);
            olds.removeAll(model.getRoles().stream().mapToInt(Role::getRid).boxed().distinct().collect(Collectors.toList()));
            for (Integer rid : news) {
                userGroupRoleDao.insertSelective(new UserGroupRole(null, model.getUgid(), rid));
            }
            for (Integer rid : olds) {
                userGroupRoleDao.delete(new UserGroupRole(null, model.getUgid(), rid));
            }
        }
        if (model.getGroupname() != null || model.getNote() != null) {
            return super.updateRecord(model);
        } else {
            return 0;
        }
    }

    @Override
    public List<Role> selectRoles(@NotEmpty List<UserGroup> userGroups) {
        List<Role> roles = new ArrayList<>();
        for (UserGroup userGroup : userGroups) {
            roles.addAll(userGroupMapper.selectRole(userGroup.getUgid())
                    .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        }
        return roles;
    }
}

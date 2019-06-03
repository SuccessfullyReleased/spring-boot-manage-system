package com.demo.rbac.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.demo.rbac.config.ResourceConstants;
import com.demo.rbac.dao.common.RoleDao;
import com.demo.rbac.dao.common.UserGroupDao;
import com.demo.rbac.dao.common.UserRoleDao;
import com.demo.rbac.dao.common.UserUserGroupDao;
import com.demo.rbac.dao.custom.UserMapper;
import com.demo.rbac.model.*;
import com.demo.rbac.service.BaseService;
import com.demo.rbac.service.UserService;
import com.demo.rbac.util.Exception.BaseException;
import com.demo.rbac.util.dao.BaseDao;
import com.demo.rbac.util.dao.FileDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    FileDao fileDao;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserGroupDao userGroupDao;
    @Autowired
    UserUserGroupDao userUserGroupDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    @Override
    public void setDao(BaseDao<User> dao) {
        super.setDao(dao);
    }

    @Cacheable(value = "users", key = "#id")
    @Override
    public User selectOneById(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id) {
        log.info("UserServiceImpl::selectOneById::id = [{}]", id);
        User user = super.selectOneById(id);
        user.setUserGroups(userMapper.selectUserGroup(user.getUid())
                .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        user.setRoles(userMapper.selectRole(user.getUid())
                .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        return user;
    }

    @Cacheable(value = "users", key = "#model")
    @Override
    public User selectOne(@NotNull User model) {
        log.info("UserServiceImpl::selectOne::model = [{}]", model);
        User user = super.selectOne(model);
        user.setUserGroups(userMapper.selectUserGroup(user.getUid())
                .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        user.setRoles(userMapper.selectRole(user.getUid())
                .stream().filter(Objects::nonNull).collect(Collectors.toList()));
        return user;
    }

    @Cacheable(value = "users_count", key = "#model")
    @Override
    public Integer selectCount(@NotNull User model) {
        log.info("UserServiceImpl::selectCount::model = [{}]", model);
        return super.selectCount(model);
    }

    @Transactional
    @Override
    public Integer insertRecord(@NotNull User model) {
        log.info("UserServiceImpl::insertRecord::model = [{}]", model);
        int result = super.insertRecord(model);
        if (CollUtil.isNotEmpty(model.getUserGroups())) {
            for (UserGroup userGroup : model.getUserGroups()) {
                if (userGroupDao.selectCount(userGroup) > 0) {
                    userUserGroupDao.insertSelective(new UserUserGroup(null, model.getUid(), userGroup.getUgid()));
                } else {
                    throw new BaseException("找不到符合" + userGroup.toString() + "的记录", HttpStatus.BAD_REQUEST);
                }
            }
        }
        if (CollUtil.isNotEmpty(model.getRoles())) {
            for (Role role : model.getRoles()) {
                if (roleDao.selectCount(role) > 0) {
                    userRoleDao.insertSelective(new UserRole(null, model.getUid(), role.getRid()));
                } else {
                    throw new BaseException("找不到符合" + role.toString() + "的记录", HttpStatus.BAD_REQUEST);
                }
            }
        }
        return result;
    }

    @Override
    public List<User> selectRecords(String keyColumn, String key, String orderColumn, String order) {
        log.info("UserServiceImpl::selectRecords::keyColumn = [{}], key = [{}], orderColumn = [{}], order = [{}]", keyColumn, key, orderColumn, order);
        String[] keys = filter(keyColumn, key, orderColumn, order);
        List<User> users;
        if (keyColumn != null && (keyColumn.equals("groupname") || keyColumn.equals("rolename"))) {
            if (keyColumn.equals("groupname")) {
                users = userMapper.selectGroupName(keys[1], keys[2], keys[3]);
                for (User user : users) {
                    user.setRoles(userMapper.selectRole(user.getUid())
                            .stream().filter(Objects::nonNull).collect(Collectors.toList()));
                }
            } else {
                users = userMapper.selectRoleName(keys[1], keys[2], keys[3]);
                for (User user : users) {
                    user.setUserGroups(userMapper.selectUserGroup(user.getUid())
                            .stream().filter(Objects::nonNull).collect(Collectors.toList()));
                }
            }
        } else {
            users = super.selectRecords(keyColumn, key, orderColumn, order);
            for (User user : users) {
                user.setUserGroups(userMapper.selectUserGroup(user.getUid())
                        .stream().filter(Objects::nonNull).collect(Collectors.toList()));
                user.setRoles(userMapper.selectRole(user.getUid())
                        .stream().filter(Objects::nonNull).collect(Collectors.toList()));
            }
        }
        return users;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public Integer deleteRecord(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id) {
        log.info("UserServiceImpl::deleteRecord::id = [{}]", id);
        return super.deleteRecord(id);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    @Override
    public void updateAvator(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id, @NotNull MultipartFile file) {
        log.info("UserServiceImpl::updateAvator::id = [{}], file = [{}]", id, file);
        File dest = fileDao.saveTo(file, ResourceConstants.AVATOR);
        User user = getDao().selectByPrimaryKey(id);
        String oldFileName = user.getAvator();
        user.setAvator(dest.getName());
        getDao().updateByPrimaryKeySelective(user);
        if (!oldFileName.equals("0.png") && !oldFileName.equals("1.png")) {
            if (!fileDao.deleteFile(new File(ResourceConstants.AVATOR + oldFileName))) {
                throw new BaseException("无法删除头像" + oldFileName, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public Integer deleteRecords(@NotEmpty List<User> list) {
        log.info("UserServiceImpl::deleteRecords::list = [{}]", list);
        return list.stream().mapToInt(User::getUid).reduce(0, (x, y) -> x + getDao().deleteByPrimaryKey(y));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    @Override
    public Integer updateRecord(@NotNull User model) {
        log.info("UserServiceImpl::updateRecord::model = [{}]", model);
        if (model.getUserGroups() != null) {
            List<Integer> news = model.getUserGroups().stream().mapToInt(UserGroup::getUgid).boxed().distinct().collect(Collectors.toList());
            List<Integer> olds = userMapper.selectUserGroup(model.getUid())
                    .stream().filter(Objects::nonNull).mapToInt(UserGroup::getUgid).boxed().distinct().collect(Collectors.toList());
            news.removeAll(olds);
            olds.removeAll(model.getUserGroups().stream().mapToInt(UserGroup::getUgid).boxed().distinct().collect(Collectors.toList()));
            for (Integer ugid : news) {
                userUserGroupDao.insertSelective(new UserUserGroup(null, model.getUid(), ugid));
            }
            for (Integer ugid : olds) {
                userUserGroupDao.delete(new UserUserGroup(null, model.getUid(), ugid));
            }
        }
        if (model.getRoles() != null) {
            List<Integer> news = model.getRoles().stream().mapToInt(Role::getRid).boxed().distinct().collect(Collectors.toList());
            List<Integer> olds = userMapper.selectRole(model.getUid())
                    .stream().filter(Objects::nonNull).mapToInt(Role::getRid).boxed().distinct().collect(Collectors.toList());
            news.removeAll(olds);
            olds.removeAll(model.getRoles().stream().mapToInt(Role::getRid).boxed().distinct().collect(Collectors.toList()));
            for (Integer rid : news) {
                userRoleDao.insertSelective(new UserRole(null, model.getUid(), rid));
            }
            for (Integer rid : olds) {
                userRoleDao.delete(new UserRole(null, model.getUid(), rid));
            }
        }
        if (model.getUsername() != null || model.getPassword() != null || model.getSex() != null
                || model.getPhone() != null || model.getMail() != null || model.getAvator() != null) {
            return super.updateRecord(model);
        } else {
            return 0;
        }
    }
}

package com.demo.cms.service;

import cn.hutool.core.util.StrUtil;
import com.demo.cms.util.Exception.BaseException;
import com.demo.cms.util.dao.BaseDao;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className BaseService
 * @description 封装了基本的CRUD业务
 * @date 2019/5/20 18:52
 **/
public abstract class BaseService<T> implements CommonService<T> {
    /**
     * @author 戴俊明
     * @description 持久层类
     * @date 2019/5/20 18:53
     **/
    private BaseDao<T> dao;

    /**
     * @param dao 持久层类
     * @return void
     * @author 戴俊明
     * @description 获取持久层（必须重写）
     * @date 2019/5/20 18:54
     **/
    public void setDao(BaseDao<T> dao) {
        this.dao = dao;
    }

    /**
     * @return com.demo.rbac.util.dao.BaseDao<T>
     * @author 戴俊明
     * @description 获取持久层类，以支持各个业务层独有的业务
     * @date 2019/5/20 18:54
     **/
    public BaseDao<T> getDao() {
        return dao;
    }

    @Override
    public T selectOneById(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id) {
        return get(dao.selectByPrimaryKey(id), "找不到id为" + id + "的记录");
    }

    @Override
    public T selectOne(@NotNull T model) {
        return get(dao.selectOne(model), "找不到符合" + model.toString() + "的记录");
    }

    @Override
    public int selectCount(@NotNull T model) {
        int num = dao.selectCount(model);
        if (num > 0) {
            return num;
        } else {
            throw new BaseException("找不到符合" + model.toString() + "的记录", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public int insertRecord(@NotNull T model) {
        return dao.insertSelective(model);
    }

    @Override
    public List<T> selectRecords(String keyColumn, String key, String orderColumn, String order) {
        String[] cons = filter(keyColumn, key, orderColumn, order);
        Class<T> TClass = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        Example example = new Example(TClass);
        if (cons[2] != null)
            example.setOrderByClause(StrUtil.format("{} {}", cons[2], cons[3]));
        if (cons[0] != null)
            example.createCriteria()
                    .andLike(cons[0], cons[1]);
        return dao.selectByExample(example);
    }

    @Transactional
    @Override
    public int deleteRecord(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Override
    public abstract int deleteRecords(@NotEmpty List<T> list);

    @Override
    public int updateRecord(@NotNull T model) {
        return dao.updateByPrimaryKeySelective(model);
    }
}

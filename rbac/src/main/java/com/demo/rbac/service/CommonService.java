package com.demo.rbac.service;

import cn.hutool.core.util.StrUtil;
import com.demo.rbac.util.Exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author 戴俊明
 * @version 1.0
 * @className CommonService
 * @description 业务层的基本CRUD接口
 * @date 2019/5/20 18:17
 **/
@Validated
public interface CommonService<T> {
    /**
     * @param model   实体（可能为null)
     * @param message 报错信息
     * @return T
     * @author 戴俊明
     * @description 判断是否空值
     * @date 2019/5/20 18:17
     **/
    default T get(T model, String message) {
        return Optional.ofNullable(model).orElseThrow(() ->
                new BaseException(message, HttpStatus.NOT_FOUND));
    }

    /**
     * @param id 主键
     * @return T
     * @author 戴俊明
     * @description 通过主键查询
     * @date 2019/5/20 18:41
     **/
    T selectOneById(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id);

    /**
     * @param model 实体
     * @return T
     * @author 戴俊明
     * @description 通过实体类的属性精确查询
     * @date 2019/5/20 18:41
     **/
    T selectOne(@NotNull T model);

    /**
     * @param model 实体
     * @return int
     * @author 戴俊明
     * @description 通过实体类的属性精确查询符合条件的记录数量
     * @date 2019/5/20 18:42
     **/
    Integer selectCount(@NotNull T model);

    /**
     * @param keyColumn   关键字的字段名
     * @param key         关键字
     * @param orderColumn 排序的字段名
     * @param order       排序规则
     * @return java.lang.String[]
     * @author 戴俊明
     * @description 模糊查询前的参数处理
     * @date 2019/5/20 18:44
     **/
    default String[] filter(String keyColumn, String key, String orderColumn, String order) {
        String[] s = new String[4];
        if (StrUtil.isEmpty(key)) {
            s[0] = s[1] = null;
        } else {
            s[0] = keyColumn;
            s[1] = StrUtil.format("%{}%", key);
        }
        if (StrUtil.isEmpty(order)) {
            s[2] = s[3] = null;
        } else {
            s[2] = orderColumn;
            s[3] = order;
        }
        return s;
    }

    /**
     * @param keyColumn   关键字的字段名
     * @param key         关键字
     * @param orderColumn 排序的字段名
     * @param order       排序规则
     * @return java.util.List<T>
     * @author 戴俊明
     * @description 模糊查询并排序
     * @date 2019/5/20 18:45
     **/
    List<T> selectRecords(String keyColumn, String key, String orderColumn, String order);

    /**
     * @param model 实体
     * @return int
     * @author 戴俊明
     * @description 插入一条记录
     * @date 2019/5/20 18:45
     **/
    Integer insertRecord(@NotNull T model);

    /**
     * @param id 主键
     * @return int
     * @author 戴俊明
     * @description 根据主键删除一条记录
     * @date 2019/5/20 18:46
     **/
    Integer deleteRecord(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id);

    /**
     * @param list 主键的数组
     * @return int
     * @author 戴俊明
     * @description 根据主键数组删除多条记录
     * @date 2019/5/20 18:46
     **/
    Integer deleteRecords(@NotEmpty List<T> list);

    /**
     * @param model 实体
     * @return int
     * @author 戴俊明
     * @description 更新一条记录
     * @date 2019/5/20 18:47
     **/
    Integer updateRecord(@NotNull T model);

}

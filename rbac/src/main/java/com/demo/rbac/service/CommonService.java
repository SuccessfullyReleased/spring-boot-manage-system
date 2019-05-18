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

@Validated
public interface CommonService<T> {

    default T get(T model, String message) {
        return Optional.ofNullable(model).orElseThrow(() ->
                new BaseException(message, HttpStatus.NOT_FOUND));
    }

    T selectOneById(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id);

    T selectOne(@NotNull T model);

    int selectCount(@NotNull T model);

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

    List<T> selectRecords(String keyColumn, String key, String orderColumn, String order);

    int insertRecord(@NotNull T model);

    int deleteRecord(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id);

    int deleteRecords(@NotEmpty List<T> list);

    int updateRecord(@NotNull T model);

}

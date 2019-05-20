package com.demo.rbac.service;

import com.demo.rbac.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 戴俊明
 * @version 1.0
 * @className UserService
 * @description 用户的业务层接口
 * @date 2019/5/20 18:56
 **/
@Validated
public interface UserService extends CommonService<User> {
    /**
     * @param id   用户主键
     * @param file 头像
     * @author 戴俊明
     * @description 上传（更新）某个用户的头像
     * @date 2019/5/20 18:57
     **/
    void updateAvator(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id, @NotNull MultipartFile file);

}

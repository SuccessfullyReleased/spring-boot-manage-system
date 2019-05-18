package com.demo.rbac.service;

import com.demo.rbac.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface UserService extends CommonService<User> {

    void updateAvator(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id, @NotNull MultipartFile file);

}

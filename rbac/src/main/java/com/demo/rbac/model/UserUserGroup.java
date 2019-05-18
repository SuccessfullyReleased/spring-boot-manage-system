package com.demo.rbac.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_usergroup")
public class UserUserGroup implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Min(value = 1, message = "id最小不能小于1")
    private Integer id;

    private Integer uid;

    private Integer ugid;
}
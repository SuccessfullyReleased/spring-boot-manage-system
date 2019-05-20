package com.demo.rbac.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className Role
 * @description 角色的实体类
 * @date 2019/5/20 16:29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Min(value = 1, message = "id最小不能小于1")
    private Integer rid;

    private String rolename;

    private String note;

    private Integer parent;

    @Transient
    private List<Access> accesses;
}
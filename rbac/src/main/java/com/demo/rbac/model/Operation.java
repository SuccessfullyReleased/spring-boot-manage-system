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

/**
 * @author 戴俊明
 * @version 1.0
 * @className Operation
 * @description 操作的实体类
 * @date 2019/5/20 16:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "operation")
public class Operation implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Min(value = 1, message = "id最小不能小于1")
    private Integer oid;

    private String name;

    private String key;

    private Integer parent;
}
package com.demo.cms.model;

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
import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * @author 戴俊明
 * @version 1.0
 * @className Floor
 * @description 楼层的实体类
 * @date 2019/5/24 8:46
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "floor")
public class Floor implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Min(value = 1, message = "id最小不能小于1")
    private Integer fid;

    private byte[] content;

    private String author;

    private Date time;

    private Integer nid;

    @Transient
    private String contentText;

    @Transient
    private List<Comment> comments;
}
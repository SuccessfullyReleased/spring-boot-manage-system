package com.demo.cms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 戴俊明
 * @version 1.0
 * @className Comment
 * @description 评论的实体类
 * @date 2019/5/24 8:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Min(value = 1, message = "id最小不能小于1")
    private Integer cid;

    private String content;

    private String author;

    private Date time;

    private Integer fid;

    private Integer pid;

}
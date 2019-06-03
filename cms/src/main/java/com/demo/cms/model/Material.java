package com.demo.cms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 戴俊明
 * @version 1.0
 * @className Material
 * @description 材料的实体类
 * @date 2019/5/27 10:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "material")
public class Material implements Serializable {

    @Id
    @KeySql(useGeneratedKeys = true)
    @Min(value = 1, message = "id最小不能小于1")
    private Integer mid;

    private String title;

    private String type;

    private String note;

    private Integer size;

    private String author;

    private Date time;

    private String file;

    @Transient
    private MultipartFile raw;
}
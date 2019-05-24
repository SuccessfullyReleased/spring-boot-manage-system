package com.demo.cms.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 戴俊明
 * @version 1.0
 * @className Floor
 * @description 楼层的实体类
 * @date 2019/5/24 8:46
 **/
public class Floor implements Serializable {
    private Integer fid;

    private String author;

    private Date time;

    private Integer nid;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
package com.demo.cms.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 戴俊明
 * @version 1.0
 * @className Comment
 * @description 评论的实体类
 * @date 2019/5/24 8:45
 **/
public class Comment implements Serializable {
    private Integer cid;

    private String content;

    private String author;

    private Date time;

    private Integer fid;

    private static final long serialVersionUID = 1L;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }
}
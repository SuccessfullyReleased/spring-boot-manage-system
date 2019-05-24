package com.demo.cms.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 戴俊明
 * @version 1.0
 * @className Note
 * @description 帖子的实体类
 * @date 2019/5/24 8:46
 **/
public class Note implements Serializable {
    private Integer nid;

    private String title;

    private String author;

    private Date time;

    private static final long serialVersionUID = 1L;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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
}
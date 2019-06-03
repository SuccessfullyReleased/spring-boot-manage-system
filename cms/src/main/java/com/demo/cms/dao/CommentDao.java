package com.demo.cms.dao;

import com.demo.cms.model.Comment;
import com.demo.cms.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @className CommentDao
 * @description 评论的通用mapper类
 * @date 2019/5/22 18:48
 **/

@Repository
public interface CommentDao extends BaseDao<Comment> {
}

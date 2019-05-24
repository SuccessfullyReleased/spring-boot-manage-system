package dao;

import model.Comment;

public interface CommentDao {
    Comment selectByPrimaryKey(Integer cid);
}
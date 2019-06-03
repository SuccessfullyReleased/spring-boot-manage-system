package com.demo.cms.service.impl;

import com.demo.cms.model.Comment;
import com.demo.cms.service.BaseService;
import com.demo.cms.util.dao.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author 戴俊明
 * @className CommentServiceImpl
 * @description 评论的业务层实现
 * @date 2019/5/24 13:33
 **/

@Slf4j
@Service
public class CommentServiceImpl extends BaseService<Comment> {

    @Autowired
    @Override
    public void setDao(BaseDao<Comment> dao) {
        super.setDao(dao);
    }

    @Override
    public Integer insertRecord(@NotNull Comment model) {
        log.info("CommentServiceImpl::insertRecord::model = [{}]", model);
        model.setTime(new Date());
        return super.insertRecord(model);
    }

    @Transactional
    @Override
    public Integer deleteRecord(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id) {
        log.info("CommentServiceImpl::deleteRecord::id = [{}]", id);
        Comment comment = new Comment();
        comment.setPid(id);
        getDao().delete(comment);
        return super.deleteRecord(id);
    }

    @Transactional
    @Override
    public Integer deleteRecords(@NotEmpty List<Comment> list) {
        log.info("CommentServiceImpl::deleteRecords::list = [{}]", list);
        return list.stream().mapToInt(Comment::getCid).reduce(0, (x, y) -> x + getDao().deleteByPrimaryKey(y));
    }
}

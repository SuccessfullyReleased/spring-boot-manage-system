package com.demo.cms.service.impl;

import com.demo.cms.model.Comment;
import com.demo.cms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
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
    @Override
    public int deleteRecords(@NotEmpty List<Comment> list) {
        log.info("CommentServiceImpl::deleteRecords::list = [{}]", list);
        return list.stream().mapToInt(Comment::getCid).reduce(0, (x, y) -> x + getDao().deleteByPrimaryKey(y));
    }
}

package com.demo.cms.service.impl;

import com.demo.cms.model.Note;
import com.demo.cms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 戴俊明
 * @className NoteServiceImpl
 * @description 帖子的业务层实现
 * @date 2019/5/24 9:00
 **/

@Slf4j
@Service
public class NoteServiceImpl extends BaseService<Note> {
    @Override
    public int deleteRecords(@NotEmpty List<Note> list) {
        log.info("NoteServiceImpl::deleteRecords::list = [{}]", list);
        return list.stream().mapToInt(Note::getNid).reduce(0, (x, y) -> x + getDao().deleteByPrimaryKey(y));
    }
}

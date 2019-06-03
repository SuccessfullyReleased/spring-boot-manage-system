package com.demo.cms.dao;

import com.demo.cms.model.Note;
import com.demo.cms.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author 戴俊明
 * @className NoteDao
 * @description 贴的通用mapper类
 * @date 2019/5/22 18:41
 **/

@Repository
public interface NoteDao extends BaseDao<Note> {
}

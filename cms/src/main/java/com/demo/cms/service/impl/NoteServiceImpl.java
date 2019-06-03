package com.demo.cms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.demo.cms.model.Floor;
import com.demo.cms.model.Note;
import com.demo.cms.service.BaseService;
import com.demo.cms.util.dao.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    @Override
    public void setDao(BaseDao<Note> dao) {
        super.setDao(dao);
    }

    @Autowired
    BaseService<Floor> floorBaseService;

    @Transactional
    @Override
    public Integer insertRecord(@NotNull Note model) {
        log.info("NoteServiceImpl::insertRecord::model = [{}]", model);
        model.setTime(new Date());
        Integer result = super.insertRecord(model);
        for (Floor floor : model.getFloors()) {
            floor.setContent(floor.getContentText().getBytes(StandardCharsets.UTF_8));
            floor.setTime(model.getTime());
            floor.setNid(model.getNid());
            floorBaseService.insertRecord(floor);
        }
        return result;
    }

    @Override
    public List<Note> selectRecords(String keyColumn, String key, String orderColumn, String order) {
        log.info("NoteServiceImpl::selectRecords::keyColumn = [{}], key = [{}], orderColumn = [{}], order = [{}]", keyColumn, key, orderColumn, order);
        Example example = new Example(Note.class);
        example.setForUpdate(true);
        example.orderBy("time").desc();
        if (keyColumn != null) {
            if (!keyColumn.equals("time")) {
                example.createCriteria().andLike(keyColumn, StrUtil.format("%{}%", key));
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String[] times = key.split("~");
                try {
                    example.createCriteria()
                            .andBetween("time", sdf.parse(times[0]), sdf.parse(times[1]));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        List<Note> notes = getDao().selectByExample(example);
        for (Note note : notes) {
            List<Floor> floors = new ArrayList<>();
            Floor floor = new Floor();
            floor.setNid(note.getNid());
            floor.setTime(note.getTime());
            floors.add(floorBaseService.selectOne(floor));
            note.setFloors(floors);
        }
        return notes;
    }

    @Transactional
    @Override
    public Integer deleteRecords(@NotEmpty List<Note> list) {
        log.info("NoteServiceImpl::deleteRecords::list = [{}]", list);
        return list.stream().mapToInt(Note::getNid).reduce(0, (x, y) -> x + getDao().deleteByPrimaryKey(y));
    }
}

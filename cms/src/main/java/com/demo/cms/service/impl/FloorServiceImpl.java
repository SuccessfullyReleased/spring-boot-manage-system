package com.demo.cms.service.impl;

import com.demo.cms.model.Floor;
import com.demo.cms.service.BaseService;
import com.demo.cms.util.dao.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @author 戴俊明
 * @className FloorServiceImpl
 * @description 楼层的业务类实现
 * @date 2019/5/24 13:38
 **/

@Slf4j
@Service
public class FloorServiceImpl extends BaseService<Floor> {

    @Autowired
    @Override
    public void setDao(BaseDao<Floor> dao) {
        super.setDao(dao);
    }

    @Override
    public Floor selectOneById(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id) {
        Floor floor = super.selectOneById(id);
        floor.setContentText(new String(floor.getContent(), StandardCharsets.UTF_8));
        return floor;
    }

    @Override
    public Floor selectOne(@NotNull Floor model) {
        Floor floor = super.selectOne(model);
        floor.setContentText(new String(floor.getContent(), StandardCharsets.UTF_8));
        return floor;
    }

    @Override
    public List<Floor> select(@NotNull Floor model) {
        List<Floor> floors = super.select(model);
        for (Floor floor : floors) {
            floor.setContentText(new String(floor.getContent(), StandardCharsets.UTF_8));
        }
        return floors;
    }

    @Transactional
    @Override
    public Integer insertRecord(@NotNull Floor model) {
        if (model.getTime()==null){
            model.setTime(new Date());
        }
        model.setContent(model.getContentText().getBytes(StandardCharsets.UTF_8));
        return super.insertRecord(model);
    }

    @Transactional
    @Override
    public Integer deleteRecords(@NotEmpty List<Floor> list) {
        return list.stream().mapToInt(Floor::getFid).reduce(0, (x, y) -> x + getDao().deleteByPrimaryKey(y));
    }

    @Transactional
    @Override
    public Integer updateRecord(@NotNull Floor model) {
        model.setContent(model.getContentText().getBytes(StandardCharsets.UTF_8));
        return super.updateRecord(model);
    }
}

package com.demo.cms.service.impl;

import com.demo.cms.model.Floor;
import com.demo.cms.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
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
    @Override
    public int deleteRecords(@NotEmpty List<Floor> list) {
        return list.stream().mapToInt(Floor::getFid).reduce(0, (x, y) -> x + getDao().deleteByPrimaryKey(y));
    }
}

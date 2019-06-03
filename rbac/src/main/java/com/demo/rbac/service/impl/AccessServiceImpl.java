package com.demo.rbac.service.impl;


import com.demo.rbac.dao.custom.AccessMapper;
import com.demo.rbac.model.Access;
import com.demo.rbac.service.AccessService;
import com.demo.rbac.service.BaseService;
import com.demo.rbac.util.dao.BaseDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Slf4j
@Service
public class AccessServiceImpl extends BaseService<Access> implements AccessService {

    @Autowired
    AccessMapper accessMapper;

    @Autowired
    @Override
    public void setDao(BaseDao<Access> dao) {
        super.setDao(dao);
    }

    @Override
    public Integer deleteRecords(@NotEmpty List<Access> list) {
        log.info("AccessServiceImpl::deleteRecords::list = [{}]", list);
        return list.stream().mapToInt(Access::getAid).reduce(0, (x, y) -> x + getDao().deleteByPrimaryKey(y));
    }

    @Cacheable(value = "accesses_all")
    @Override
    public List<Access> selectAll() {
        log.info("AccessServiceImpl::selectAll::");
        List<Access> accesses = getDao().selectAll();
        for (Access access : accesses) {
            access.setMenu(accessMapper.selectById(access.getAid()).getMenu());
            access.setOperation(accessMapper.selectById(access.getAid()).getOperation());
        }
        return accesses;
    }
}

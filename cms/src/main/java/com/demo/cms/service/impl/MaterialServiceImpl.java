package com.demo.cms.service.impl;

import com.demo.cms.config.ResourceConstants;
import com.demo.cms.model.Material;
import com.demo.cms.service.BaseService;
import com.demo.cms.util.Exception.BaseException;
import com.demo.cms.util.dao.BaseDao;
import com.demo.cms.util.dao.FileDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @author 戴俊明
 * @className MaterialServiceImpl
 * @description 材料的业务类实现
 * @date 2019/5/27 10:56
 **/

@Slf4j
@Service
public class MaterialServiceImpl extends BaseService<Material> {

    @Autowired
    @Override
    public void setDao(BaseDao<Material> dao) {
        super.setDao(dao);
    }

    @Autowired
    FileDao fileDao;

    @Transactional
    @Override
    public Integer insertRecord(@NotNull Material model) {
        model.setTitle(model.getRaw().getOriginalFilename());
        model.setSize((int) model.getRaw().getSize());
        model.setTime(new Date());
        File dest = fileDao.saveTo(model.getRaw(), ResourceConstants.MATERIALS);
        model.setFile(dest.getName());
        return super.insertRecord(model);
    }

    @Transactional
    @Override
    public Integer deleteRecord(@NotNull @Min(value = 1, message = "id最小不能小于1") Integer id) {
        Material material = getDao().selectByPrimaryKey(id);
        int result = super.deleteRecord(id);
        if (!fileDao.deleteFile(new File(ResourceConstants.MATERIALS + material.getFile()))) {
            throw new BaseException("无法删除文件" + material.getTitle(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @Transactional
    @Override
    public Integer deleteRecords(@NotEmpty List<Material> list) {
        return list.stream().mapToInt(Material::getMid).reduce(0, (x, y) -> x + deleteRecord(y));
    }
}

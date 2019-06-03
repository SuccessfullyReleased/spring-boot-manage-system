package com.demo.cms.control.impl;

import com.demo.cms.config.ResourceConstants;
import com.demo.cms.control.BaseControl;
import com.demo.cms.control.MaterialControl;
import com.demo.cms.model.Material;
import com.demo.cms.service.BaseService;
import com.demo.cms.util.Exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 戴俊明
 * @className MaterialControlImpl
 * @description 材料的表现层
 * @date 2019/5/27 10:59
 **/

@Slf4j
@RestController
public class MaterialControlImpl extends BaseControl<Material> implements MaterialControl {

    @Autowired
    @Override
    public void setService(BaseService<Material> service) {
        super.setService(service);
    }

    @GetMapping(value = "material/mid/{id}")
    @Override
    public ResponseEntity selectOneById(@PathVariable Integer id) {
        return super.selectOneById(id);
    }

    @GetMapping(value = "material")
    @Override
    public ResponseEntity selectOne(@RequestParam String model) {
        return super.selectOne(model);
    }

    @GetMapping(value = "materials")
    @Override
    public ResponseEntity selectRecords(String keyColumn, String key, String orderColumn, String order) {
        return super.selectRecords(keyColumn, key, orderColumn, order);
    }

    @DeleteMapping(value = "material/mid/{id}")
    @Override
    public ResponseEntity deleteRecord(@PathVariable Integer id) {
        return super.deleteRecord(id);
    }

    @DeleteMapping(value = "materials")
    @Override
    public ResponseEntity deleteRecords(@RequestBody List<Material> list) {
        return super.deleteRecords(list);
    }

    @PostMapping(value = "material")
    @Override
    public ResponseEntity uploadMaterial(@RequestParam String type,
                                         @RequestParam String note,
                                         @RequestParam String author,
                                         @RequestParam MultipartFile file) {
        log.info("MaterialControlImpl::uploadMaterial::type = [{}], note = [{}], author = [{}], file = [{}]", type, note, author, file);
        BaseService<Material> service = getService();
        return new ResponseEntity<>(service.insertRecord(
                new Material(null, null, type, note, null, author, null, null, file)), HttpStatus.CREATED);
    }

    @PostMapping(value = "materials")
    @Override
    public void downloadMaterial(@RequestBody Material material, HttpServletResponse response) {
        log.info("MaterialControlImpl::downloadMaterial::material = [{}], response = [{}]", material, response);
        File file = new File(ResourceConstants.MATERIALS + material.getFile());
        if (!file.exists()) {
            deleteRecord(material.getMid());
            throw new BaseException("找不到该文件", HttpStatus.NOT_FOUND);
        } else {
            InputStream inputStream = null;
            try {
                inputStream = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[inputStream.available()];
                inputStream.read(buffer);
                inputStream.close();
                String fileName = material.getTitle();
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.addHeader("Content-Disposition",
                        "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
                response.addHeader("Content-Length", "" + file.length());

                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                outputStream.write(buffer);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                throw new BaseException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}

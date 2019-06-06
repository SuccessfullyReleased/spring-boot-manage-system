package com.demo.cms.control.impl;

import com.demo.cms.authorization.annotation.NoAuthorization;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
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

    @NoAuthorization
    @GetMapping(value = "materials/file")
    @Override
    public void downloadMaterial(@RequestParam String material, HttpServletResponse response) {
        log.info("MaterialControlImpl::downloadMaterial::material = [{}], response = [{}]", material, response);
        Material model = getModel(material);
        File file = new File(ResourceConstants.MATERIALS + model.getFile());
        String fileName = model.getTitle();
        if (!file.exists()) {
            deleteRecord(model.getMid());
            throw new BaseException("找不到该文件", HttpStatus.NOT_FOUND);
        } else {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition",
                    "attachment;filename*=UTF-8''" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.addHeader("Content-Length", "" + file.length());

            response.setHeader("Accept-Ranges", "bytes");
            long downloadSize = file.length();
            long fromPos = 0, toPos = 0;
            if (getRequest().getHeader("Range") == null) {
                response.setHeader("Content-Length", downloadSize + "");
            } else {
                // 若客户端传来Range，说明之前下载了一部分，设置206状态(SC_PARTIAL_CONTENT)
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                String range = getRequest().getHeader("Range");
                String bytes = range.replaceAll("bytes=", "");
                String[] ary = bytes.split("-");
                fromPos = Long.parseLong(ary[0]);
                if (ary.length == 2) {
                    toPos = Long.parseLong(ary[1]);
                }
                int size;
                if (toPos > fromPos) {
                    size = (int) (toPos - fromPos);
                } else {
                    size = (int) (downloadSize - fromPos);
                }
                response.setHeader("Content-Length", size + "");
                downloadSize = size;
            }

            RandomAccessFile in = null;
            ServletOutputStream out = null;
            try {
                in = new RandomAccessFile(file, "rw");
                // 设置下载起始位置
                if (fromPos > 0) {
                    in.seek(fromPos);
                }
                // 缓冲区大小
                int bufLen = (int) (downloadSize < 2048 ? downloadSize : 2048);
                byte[] buffer = new byte[bufLen];
                int num;
                int count = 0; // 当前写到客户端的大小
                out = response.getOutputStream();
                while ((num = in.read(buffer)) != -1) {
                    out.write(buffer, 0, num);
                    count += num;
                    //处理最后一段，计算不满缓冲区的大小
                    if (downloadSize - count < bufLen) {
                        bufLen = (int) (downloadSize - count);
                        if (bufLen == 0) {
                            break;
                        }
                        buffer = new byte[bufLen];
                    }
                }
                response.flushBuffer();
            } catch (IOException e) {
                log.info("数据被暂停或中断。");
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

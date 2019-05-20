package com.demo.rbac.util.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 戴俊明
 * @version 1.0
 * @className FileDao
 * @description 文件操作
 * @date 2019/5/20 18:58
 **/
@Repository
public class FileDao {
    /**
     * @param file 文件
     * @param path 地址
     * @return java.io.File
     * @author 戴俊明
     * @description 保存一个文件到指定位置
     * @date 2019/5/20 18:58
     **/
    public File saveTo(MultipartFile file, String path) {
        if (file.isEmpty()) {
            return null;
        }
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        File dest = new File(path + uuid + suffix);
        try {
            file.transferTo(dest);
            return dest;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param file 文件
     * @return boolean
     * @author 戴俊明
     * @description 删除某一个文件
     * @date 2019/5/20 18:59
     **/
    public boolean deleteFile(File file) {
        return file.delete();
    }

}

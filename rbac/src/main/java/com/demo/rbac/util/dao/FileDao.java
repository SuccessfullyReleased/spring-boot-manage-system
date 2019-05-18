package com.demo.rbac.util.dao;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Repository
public class FileDao {

    public File saveTo(MultipartFile file,String path) {
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

    public boolean deleteFile(File file){
        return file.delete();
    }

}

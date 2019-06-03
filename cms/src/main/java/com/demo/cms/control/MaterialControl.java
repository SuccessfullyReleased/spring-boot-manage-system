package com.demo.cms.control;

import com.demo.cms.model.Material;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 戴俊明
 * @className MaterialControl
 * @description TODO
 * @date 2019/5/27 13:46
 **/

public interface MaterialControl {

    ResponseEntity uploadMaterial(String type, String note, String author, MultipartFile file);

    void downloadMaterial(Material material, HttpServletResponse response);

}

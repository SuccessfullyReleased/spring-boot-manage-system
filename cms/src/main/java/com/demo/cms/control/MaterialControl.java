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

    /**
     * @param type   材料类型
     * @param note   材料简介
     * @param author 材料的上传人
     * @param file   材料
     * @return org.springframework.http.ResponseEntity
     * @author 戴俊明
     * @description 上传一份材料
     * @date 2019/6/3 14:43
     **/
    ResponseEntity uploadMaterial(String type, String note, String author, MultipartFile file);

    /**
     * @param material 材料的实体
     * @param response HTTP响应
     * @return void
     * @author 戴俊明
     * @description 下载一份材料
     * @date 2019/6/3 14:44
     **/
    void downloadMaterial(String material, HttpServletResponse response);

}

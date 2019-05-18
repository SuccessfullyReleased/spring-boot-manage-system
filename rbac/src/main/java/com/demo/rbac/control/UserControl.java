package com.demo.rbac.control;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UserControl {

    ResponseEntity updateAvator(Integer uid, MultipartFile file);

    ResponseEntity createToken(String username);

    ResponseEntity checkToken();

}

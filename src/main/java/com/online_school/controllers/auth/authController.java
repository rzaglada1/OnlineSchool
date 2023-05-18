package com.online_school.controllers.auth;

import com.online_school.dto.dto_post.UserPostDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class authController {

    @PostMapping("/auth/registration")
    public ResponseEntity<?> registration(@RequestBody UserPostDTO userPostDTO) {
        System.out.println("User post DTO " + userPostDTO);
        return  new ResponseEntity<>(HttpStatus.OK);
    }



}

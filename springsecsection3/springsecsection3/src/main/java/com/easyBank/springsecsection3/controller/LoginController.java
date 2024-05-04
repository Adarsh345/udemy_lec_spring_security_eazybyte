package com.easyBank.springsecsection3.controller;

import com.easyBank.springsecsection3.controller.repository.UserRepository;
import com.easyBank.springsecsection3.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Users> addUser(@RequestBody Users users){
        Users saveUser = null;
        ResponseEntity response  = null;
        try {
            saveUser = userRepository.save(users);
            if(!saveUser.getId().isEmpty()){
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Give user details are successfully registered");
            }
        }catch (Exception exception){
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to "+exception.getMessage());
        }
        return  response;
    }
    
}

package com.easyBank.customAuthencationProviderDemo.controller;

import com.easyBank.customAuthencationProviderDemo.controller.repository.UserRepository;
import com.easyBank.passwordEncoderDemo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Users> addUser(@RequestBody Users users){
        Users saveUser = null;
        ResponseEntity response  = null;
        try {
             String hashPasword = passwordEncoder.encode(users.getPassword());
             users.setPassword(hashPasword);
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

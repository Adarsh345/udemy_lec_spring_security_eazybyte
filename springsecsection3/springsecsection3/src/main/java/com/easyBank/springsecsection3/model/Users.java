package com.easyBank.springsecsection3.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "User")
public class Users {
    @Id
    private String id;
    private String firstName;
    private String lastName; 
    private String email;
    private String password;
    private List<String> roles;
    // Getters and setters
}
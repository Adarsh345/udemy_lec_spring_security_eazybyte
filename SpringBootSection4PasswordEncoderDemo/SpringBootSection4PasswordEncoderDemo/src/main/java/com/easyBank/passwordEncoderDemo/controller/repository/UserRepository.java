package com.easyBank.passwordEncoderDemo.controller.repository;

import com.easyBank.passwordEncoderDemo.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<Users, Serializable> {
    List<Users> findByEmail(String username);
}
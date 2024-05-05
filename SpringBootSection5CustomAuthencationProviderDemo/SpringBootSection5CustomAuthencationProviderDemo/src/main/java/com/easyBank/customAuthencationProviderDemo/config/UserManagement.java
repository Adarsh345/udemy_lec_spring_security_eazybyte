package com.easyBank.customAuthencationProviderDemo.config;


import com.easyBank.customAuthencationProviderDemo.controller.repository.UserRepository;
import com.easyBank.passwordEncoderDemo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagement implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String email, password = null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Users> usersList = userRepository.findByEmail(username);
        System.out.println(usersList);

        if(usersList.isEmpty()) {
            throw new UsernameNotFoundException("User details not fond for the email :"+username);
        }else {
            email = usersList.get(0).getEmail();
            password = usersList.get(0).getPassword();
            authorities.add(new SimpleGrantedAuthority(usersList.get(0).getRoles().get(0)));
        }
        return new User(email , password , authorities);

    }
}

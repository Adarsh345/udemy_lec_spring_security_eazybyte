package com.easyBank.customAuthencationProviderDemo.config;

import com.easyBank.customAuthencationProviderDemo.controller.repository.UserRepository;
import com.easyBank.passwordEncoderDemo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
* this is the custom implementation of CustomAuthencationProviderImplementation
* */

@Component
public class CustomAuthencationProviderImplementation implements AuthenticationProvider {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
         String userName = authentication.getName();
         String password = authentication.getCredentials().toString();
         List<Users> usersList = userRepository.findByEmail(userName);

         if(usersList.isEmpty()){
             throw new UsernameNotFoundException("User not fond with the passe username : "+ userName);
         }
         
         if(passwordEncoder.matches(password, usersList.get(0).getPassword())) {
             List<GrantedAuthority> authorities = new ArrayList<>();
             authorities.add(new SimpleGrantedAuthority(usersList.get(0).getRoles().get(0)));
             return new UsernamePasswordAuthenticationToken(userName, password, authorities);

         } else {
             throw new BadCredentialsException("Invalid password");
         }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}

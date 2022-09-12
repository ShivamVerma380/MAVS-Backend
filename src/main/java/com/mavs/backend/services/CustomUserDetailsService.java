package com.mavs.backend.services;


import java.util.ArrayList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mavs.backend.daos.AdminDao;
import com.mavs.backend.entities.Admin;
import com.mavs.backend.helper.JwtUtil;

@Component
public class CustomUserDetailsService implements UserDetailsService{


    @Autowired
    public JwtUtil jwtUiUtil;

    @Autowired
    public AdminDao adminDao;

    @Autowired
    public Admin adminRequest;

    public Admin findByAdminname(String email){
        return adminDao.findAdminByEmail(email);
    }
    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
       //username means email 
        
        adminRequest = findByAdminname(useremail);
        if(adminRequest != null){

            return  new User(adminRequest.getEmail(), adminRequest.getPassword(), new ArrayList<>()); 
        }
        else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    public ResponseEntity<?> getUserByToken(String token){
        try {
            String email = jwtUiUtil.extractUsername(token);
            UserDetails uDetails = loadUserByUsername(email);  //username == email id
            return ResponseEntity.ok(uDetails); 
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


}
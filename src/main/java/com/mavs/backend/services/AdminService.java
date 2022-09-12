package com.mavs.backend.services;

import java.security.DrbgParameters.Reseed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mavs.backend.config.MySecurityConfig;
import com.mavs.backend.daos.AdminDao;
import com.mavs.backend.entities.Admin;
import com.mavs.backend.entities.JwtResponse;
import com.mavs.backend.helper.JwtUtil;
import com.mavs.backend.helper.ResponseMessage;

@Component
public class AdminService {
    
    @Autowired
    public AdminDao adminDao;

    

    @Autowired
    public MySecurityConfig mySecurityConfig;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtResponse jwtResponse;

    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    public CustomUserDetailsService customUserDetailsService;


    @Autowired
    public Admin admin;

    public ResponseEntity<?> addAdmin(String email,String password,String name){
        try {
            Admin admin = adminDao.findAdminByEmail(email);
            if(admin!=null){
                responseMessage.setMessage("An account already exists with this email");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseMessage);
            }
            //Now you can add the admin
            String encoded_password = mySecurityConfig.passwordEncoder().encode(password);
            Admin new_admin = new Admin(email,name,encoded_password);
            adminDao.save(new_admin);
            
            //spring security
            System.out.println("Before authentication manager");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
            System.out.println("After authentication manager");
            //Admin is authenticated successfully
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(new_admin.getEmail());
            String token = jwtUtil.generateToken(userDetails);


            jwtResponse.setMessage("Admin registered successfully");
            jwtResponse.setToken(token);
            return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
}

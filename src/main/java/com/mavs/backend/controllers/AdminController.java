package com.mavs.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mavs.backend.helper.ResponseMessage;
import com.mavs.backend.services.AdminService;

@RestController
public class AdminController {

    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public AdminService adminService;
    
    @PostMapping("/register")
    public ResponseEntity<?> addAdmin(@RequestParam("email") String email,@RequestParam("password")String password,@RequestParam("name")String name){
        try {
            return adminService.addAdmin(email, password, name);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        
        }
    }
}

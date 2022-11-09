package com.mavs.backend.controllers.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mavs.backend.helper.ResponseMessage;
import com.mavs.backend.services.home.HomeService;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {
    
    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public HomeService homeService;

    @PostMapping("/navbar")
    public ResponseEntity<?> addNavbarDetails(@RequestHeader("Authorization") String authorization,@RequestParam("name") String name,@RequestParam("mainlink") String mainlink,@RequestParam("submenu") String submenu){
        try {
            return homeService.addNavbarDetails(authorization, name, mainlink, submenu);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
            
        }
    }
}

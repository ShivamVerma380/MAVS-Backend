package com.mavs.backend.controllers.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public ResponseEntity<?> getWelcomeMessage(){
        try {
            responseMessage.setMessage("Welcome");
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }

    @GetMapping("/getNavbar")
    public ResponseEntity<?> getNavbarDetails(){
        try {
            return homeService.getNavbarDetails();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
            
        }
    }

    @PostMapping("/homecover")
    public ResponseEntity<?> addHomeCovers(@RequestParam("video") String video,@RequestParam("coverdescription") String description,@RequestHeader("Authorization") String authorization){
        try {
            return homeService.addHomeCovers(authorization, video, description);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }

    @GetMapping("/gethomecover")
    public ResponseEntity<?> getHomeCovers(){
        try {
            return homeService.getHomeCovers();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }

    @PostMapping("/achievements")
    public ResponseEntity<?> addAchievements(@RequestHeader("Authorization") String authorization,@RequestParam("img") String img){
        try {
            return homeService.addAchievements(authorization, img);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }

    @DeleteMapping("/deleteAchievements")
    public ResponseEntity<?> deleteAchievements(@RequestHeader("Authorization") String authorization,@RequestParam("img") String img){
        try {
            return homeService.deleteAchievements(authorization,img);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }

    @GetMapping("/getAchievements")
    public ResponseEntity<?> getAchievements(){
        try {
            return homeService.getAchievements();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }

    @PostMapping("/companydescription")
    public ResponseEntity<?> addCompanyDescription(@RequestHeader("Authorization") String authorization,@RequestParam("title") String title,@RequestParam("description") String description){
        try {
            return homeService.addCompanyDescription(authorization, title, description);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }

    @DeleteMapping("/deleteCompanyDescription")
    public ResponseEntity<?> deleteCompanyDescription(@RequestHeader("Authorization") String authorization,@RequestParam("title") String title){
        try {
            return homeService.deleteCompanyDescription(authorization,title);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }

    @GetMapping("/getCompanyDescription")
    public ResponseEntity<?> getCompanyDescription(){
        try {
            return homeService.getCompanyDescription();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((responseMessage));
        }
    }
    
}

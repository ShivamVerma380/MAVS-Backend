package com.mavs.backend.controllers.admin;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mavs.backend.entities.JwtResponse;
import com.mavs.backend.helper.JwtUtil;
import com.mavs.backend.helper.ResponseMessage;
import com.mavs.backend.services.admin.AdminService;

import io.jsonwebtoken.impl.DefaultClaims;

@RestController
public class AdminController {

    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public AdminService adminService;

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    public JwtResponse jwtResponse;
    
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

    @PostMapping("/login-admin")
    public ResponseEntity<?> loginAdmin(@RequestParam("email") String email,@RequestParam("password") String password){
        try {
            return adminService.loginAdmin(email,password);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception{
        // From the HttpRequest get the claims
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

        Map<String,Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);

        String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString()); 

        jwtResponse.setMessage("Token refreshed successfully!!");
        jwtResponse.setToken(token);
        return ResponseEntity.ok(jwtResponse);


    }

    private Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
		for (Entry<String, Object> entry : claims.entrySet()) {
			expectedMap.put(entry.getKey(), entry.getValue());
		}
		return expectedMap;
    }
}

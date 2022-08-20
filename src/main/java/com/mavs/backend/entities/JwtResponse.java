package com.mavs.backend.entities;

import org.springframework.stereotype.Component;

@Component
public class JwtResponse {
    
    private String message;

    private String token;

    public JwtResponse() {
    }

    public JwtResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JwtResponse [message=" + message + ", token=" + token + "]";
    }
    

    
}

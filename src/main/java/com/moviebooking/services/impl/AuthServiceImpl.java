package com.moviebooking.services.impl;

import org.springframework.stereotype.Service;

import com.moviebooking.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String ADMIN_LOGIN_ID = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @Override
    public String adminLogin(String loginId, String password) {
        if (ADMIN_LOGIN_ID.equals(loginId) && ADMIN_PASSWORD.equals(password)) {
            return "Admin login successful!";
        } else {
            throw new IllegalArgumentException("Invalid admin credentials");
        }
    }
}

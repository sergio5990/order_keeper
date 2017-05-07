package com.sashaq.service.bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public interface TokenService {
    String buildNewToken(String username);
}

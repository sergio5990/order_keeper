package com.sashaq.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * Created by SashaQ on 27.04.2017.
 */
public interface TokenService {
    String buildNewToken(String username);
}

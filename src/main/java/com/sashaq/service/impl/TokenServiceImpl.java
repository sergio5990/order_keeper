package com.sashaq.service.impl;

import com.sashaq.service.TokenService;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {
    private SecureRandom random;

    @PostConstruct
    private void init(){
        random = new SecureRandom();
    }

    @Override
    public String buildNewToken(String username) {
        return new BigInteger(512, random).toString(32).toUpperCase();
    }
}

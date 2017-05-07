package com.sashaq.service.bean.impl;

import com.sashaq.service.bean.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;

@Service
public class TokenServiceImpl implements TokenService {
    private final SecureRandom random =  new SecureRandom();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String buildNewToken(String username) {
        String encodedUsername = passwordEncoder.encode(username);
        String randomSequence = new BigInteger(512, random).toString(32);

        return (randomSequence + encodedUsername).toUpperCase();
    }
}

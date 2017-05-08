package com.sashaq.service.bean.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.security.SecureRandom;

public class TokenService {
    private static final int NUM_BITS = 512;
    private static final int RADIX = 32;
    private static final SecureRandom random =  new SecureRandom();
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String generateToken(String username) {
        String encodedUsername = passwordEncoder.encode(username);
        String randomSequence = new BigInteger(NUM_BITS, random).toString(RADIX);

        return (randomSequence + encodedUsername).toUpperCase();
    }
}

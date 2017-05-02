package com.sashaq.service.impl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigInteger;
import java.security.SecureRandom;

import static org.junit.Assert.*;

/**
 * Created by SashaQ on 27.04.2017.
 */
public class TokenServiceImplTest {
    TokenServiceImpl tokenService = new TokenServiceImpl();

    @Test
    public void buildNewToken(){
        String token = tokenService.buildNewToken("123");
        assertNotNull(token);
        System.out.println(token);
    }

    @Test
    public void name() {
        SecureRandom random = new SecureRandom();
        String s = new BigInteger(512, random).toString(32).toUpperCase();
        System.out.println(new BigInteger(512, random).toString(32).toUpperCase());
        assertNotNull(s);
        System.out.println(s);
    }
}
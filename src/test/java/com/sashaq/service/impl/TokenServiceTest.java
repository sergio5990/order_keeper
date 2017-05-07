package com.sashaq.service.impl;

import com.sashaq.service.bean.impl.TokenService;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TokenServiceTest {

    @Test
    public void buildNewToken(){
        String token = TokenService.generateToken("123");
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }
}
package com.sashaq.service.impl;

import com.sashaq.service.bean.TokenService;
import com.sashaq.service.bean.impl.TokenServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class TokenServiceImplTest {
    private TokenService tokenService = new TokenServiceImpl();

    @Test
    public void buildNewToken(){
        String token = tokenService.buildNewToken("123");
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }
}
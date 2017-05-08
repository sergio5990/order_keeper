package com.sashaq.service.bean.impl;

import com.sashaq.dao.AuthenticationDao;
import com.sashaq.exception.BadCredentialException;
import com.sashaq.service.bean.AuthenticationService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationDao authenticationDao;

    public AuthenticationServiceImpl(final AuthenticationDao authenticationDao) {
        this.authenticationDao = authenticationDao;
    }

    @Override
    @Transactional
    public String login(String username, String password) {
        Integer userId = null;
        try {
            userId = authenticationDao.canLogin(username, password);
        } catch (EmptyResultDataAccessException e) {
            throw new BadCredentialException();
        }

        LocalDateTime nowDate = LocalDateTime.now();
        LocalDateTime expireDate = nowDate.plusWeeks(2);

        String token = TokenService.generateToken(username);
        authenticationDao.saveOrUpdateToken(token, userId, expireDate);

        return token;
    }
}

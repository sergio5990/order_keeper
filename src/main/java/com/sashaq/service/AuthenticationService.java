package com.sashaq.service;

import com.sashaq.entity.User;

public interface AuthenticationService {
    String login(String username, String password);
}

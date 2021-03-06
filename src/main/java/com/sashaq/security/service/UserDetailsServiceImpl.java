package com.sashaq.security.service;


import com.sashaq.entity.User;
import com.sashaq.security.entity.SecurityUser;
import com.sashaq.service.bean.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.sashaq.core.util.constant.StringConstant.INVALID_TOKEN;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailsServiceImpl(final UserService userService) {
        this.userService = userService;
    }

    /**
     * load by token, wrong method name
     */
    @Override
    public UserDetails loadUserByUsername(final String token) throws UsernameNotFoundException {
        if (isBlank(token)) {
            throw new UsernameNotFoundException(INVALID_TOKEN);
        }

        User user = userService.getByToken(token);
        if (user != null) {
            return new SecurityUser(user);
        } else {
            throw new UsernameNotFoundException(INVALID_TOKEN);
        }
    }
}

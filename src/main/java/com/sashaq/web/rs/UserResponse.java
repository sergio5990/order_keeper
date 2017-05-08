package com.sashaq.web.rs;

import com.sashaq.entity.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private final Integer id;
    private final String username;
    private final String name;
    private final String surname;
    private final String email;

    public UserResponse(final User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
    }
}

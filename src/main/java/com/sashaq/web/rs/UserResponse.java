package com.sashaq.web.rs;

import com.sashaq.entity.User;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;

@Getter
public class UserResponse {
    private Integer id;
    private String username;
    private String name;
    private String surname;
    private String email;

    public UserResponse(User user) {
        if (user != null) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.name = user.getName();
            this.surname = user.getSurname();
            this.email = user.getEmail();
        }
    }
}

package com.sashaq.entity;

import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;

    public User() {
    }

    public User(Integer id, String username, String name, String surname, String email, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public User(Integer id, String username, String name, String surname, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}

package com.sashaq.service.builder;

import com.sashaq.entity.User;

public class UserBuilder {
    private Integer id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;

    public UserBuilder id(Integer id) {
        this.id = id;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public User build(){
        return new User(id, username, name, surname, email, password);
    }

}

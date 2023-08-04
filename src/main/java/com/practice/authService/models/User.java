package com.practice.authService.models;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String date;

    public User() {}

    public User(String login, String password, String date){
        this.login = login;
        this.password = password;
        this.date = date;
    }

    @Override
    public String toString() {
        return "{\"login\":\"" + password + "\"," + "\"registrationDate\":\"" + date + "\"}";
    }
}

package com.practice.authService.models;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Getter @Setter private String date;
    @Getter @Setter private String email;

    public User() {}

    public User(String login, String password, String date, String email){
        this.login = login;
        this.password = password;
        this.date = date;
        this.email = email;
    }

    @Override
    public String toString() {
        return "{\"login\":\"" + login + "\"," + "\"registrationDate\":\"" + date + "\"}";
    }

    public String fullToString(){
        return "{\"login\":\"" + login + "\", \"password\":\"" + password + "\", \"registrationDate\":\""
                + date + "\", \"email\":\"" + email + "\"}";
    }
}

package com.practice.authService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import com.practice.authService.models.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {

    @PostMapping
    public ResponseEntity<String> Post(@RequestBody User user) throws IllegalArgumentException{
        if (user.getDate() == null) {
            Date registrationDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            user.setDate(formatter.format(registrationDate));
        }
        if (user.getLogin() == null || user.getPassword() == null || user.getEmail() == null) {
            throw new IllegalArgumentException("Login, password or email must not be null.");
        }
        DatabaseController dbc = new DatabaseController();
        dbc.insertUserData(user);
        return ResponseEntity.ok(user + " added");
    }

    @GetMapping(value = "/login={login}")
    public ResponseEntity<String> Get(@PathVariable String login) throws NoSuchElementException{
        DatabaseController dbc = new DatabaseController();
        User user = dbc.selectByLogin(login);
        if(user.getLogin() == null){
            throw new NoSuchElementException("User with login \"" + login + "\" not found");
        }
        return ResponseEntity.ok(user.fullToString());
    }
}
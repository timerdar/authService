package com.practice.authService;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.practice.authService.models.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {

    @PostMapping
    public ResponseEntity<String> Post(@RequestBody User user) throws IllegalArgumentException{
            if (user.getDate() == null) {
                Date registrationDate = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                user.setDate(formatter.format(registrationDate));
            }
            if (user.getLogin() == null || user.getPassword() == null){
                throw new IllegalArgumentException("Login and password must not be null.");
            }
            return ResponseEntity.ok(user.toString());
    }

    @GetMapping
    public ResponseEntity<String> Get(){
        String message = "{\"message\":\"HelloWorld\"}";
        return ResponseEntity.ok(message);
    }
}
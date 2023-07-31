package com.practice.authService;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.practice.authService.models.PostAns;
import com.practice.authService.models.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class AuthController {

    @PostMapping("/")
    public PostAns getLoginData(User user){
        return PostAns(user.login(), )
    }
}
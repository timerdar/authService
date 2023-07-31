package com.practice.authService;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.practice.authService.models.PostAns;
import com.practice.authService.models.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
@RestController
public class AuthController {

    @PostMapping("/auth")
    public ResponseEntity<PostAns> getLoginData(@RequestBody User user){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        PostAns res = new PostAns(user.login(), formatter.format(date));
        return ResponseEntity.ok(res);
    }


}
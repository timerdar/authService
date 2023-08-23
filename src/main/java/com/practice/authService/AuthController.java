package com.practice.authService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import com.practice.authService.models.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {

    private static final String fileName = "get_logs.txt";

    @PostMapping
    public ResponseEntity<String> PostToDB(@RequestBody User user) throws IllegalArgumentException{
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
    public ResponseEntity<String> GetFromDB(@PathVariable String login) throws NoSuchElementException{
        DatabaseController dbc = new DatabaseController();
        User user = dbc.selectByLogin(login);
        if(user.getLogin() == null){
            throw new NoSuchElementException("User with login \"" + login + "\" not found");
        }

        LogController writer = new LogController(fileName);
        writer.writeToFile(user.fullToString());
        return ResponseEntity.ok(user.fullToString());
    }

    @GetMapping("/userslogs")
    public ResponseEntity<String> GetFromLogs() throws IOException {
        LogController reader = new LogController(fileName);
        String randomLine = reader.readRandomLine();
        if (randomLine == null){
            throw new NoSuchElementException("Logs are empty");
        }
        return ResponseEntity.ok(randomLine);
    }
}
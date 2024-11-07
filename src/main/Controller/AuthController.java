package com.example.demo.Controller;    
import com.example.demo.Service.JwtUtil;
import com.example.demo.Service.SessionManager;
import com.example.demo.Service.UserService;    
import com.example.demo.Entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

 
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SessionManager sessionManager;

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        userService.registerUser(username, password);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (userService.authenticate(username, password)) {
            String token = jwtUtil.generateToken(username);
            sessionManager.addSession(username, token);
            return token;
        } else {
            return "Invalid username or password!";
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam String token) {
        String username = jwtUtil.extractUsername(token);
        if (sessionManager.isSessionValid(username, token)) {
            return "Token is valid!";
        } else {
            return "Invalid or expired token!";
        }
    }
}

package com.example.redistemplate.controller;

import com.example.redistemplate.config.security.JwtAuthenticationResponse;
import com.example.redistemplate.config.security.JwtTokenProvider;
import com.example.redistemplate.dao.UserDao;
import com.example.redistemplate.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import redis.clients.jedis.Jedis;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    private Jedis jedis;

    @Autowired
    private UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired JwtTokenProvider tokenProvider;

    @ResponseBody
    @PostMapping("/register")
    @CrossOrigin(origins = { "http://127.0.0.1:3000", "http://localhost:3000" })
    public ResponseEntity<?> register(@RequestBody UserAccountVo userAccountVo) {
        if (userDao.existsByUsername(userAccountVo.getUserName())) {
            MessageResponse messageResponse = new MessageResponse("Username is already taken!", ResponseStatus.ERROR);
            return new ResponseEntity(messageResponse,
                    HttpStatus.BAD_REQUEST);
        }
        userDao.createNewUser(User.builder().name("default").username(userAccountVo.getUserName())
                .password(userAccountVo.getPassword()).build());
        MessageResponse messageResponse = new MessageResponse("Sign up successfully!", ResponseStatus.SUCCESS);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(userAccountVo.getUserName()).toUri();
        return ResponseEntity.created(location).body(messageResponse);
    }

    @ResponseBody
    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity login(@RequestBody UserAccountVo userAccountVo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAccountVo.getUserName(),
                        userAccountVo.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @ResponseBody
    @PostMapping("/main")
    @CrossOrigin(origins = "http://localhost:3000")
    public String test(){
        return "test";
    }
}

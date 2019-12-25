package com.example.redistemplate.controller;

import com.example.redistemplate.config.security.JwtAuthenticationResponse;
import com.example.redistemplate.config.security.JwtTokenProvider;
import com.example.redistemplate.controller.vo.UserAccountVo;
import com.example.redistemplate.dao.spec.UserDao;
import com.example.redistemplate.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import redis.clients.jedis.Jedis;

import java.net.URI;

@Controller
public class LoginController {

    @Autowired
    private Jedis jedis;

    @Autowired
    private UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired JwtTokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAccountVo userAccountVo) {
        if (userDao.existsByUsername(userAccountVo.getUsername())) {
            MessageResponse messageResponse = new MessageResponse("Username is already taken!", ResponseStatus.ERROR);
            return new ResponseEntity(messageResponse,
                    HttpStatus.BAD_REQUEST);
        }
        userDao.createNewUser(
                User.builder().lastName(userAccountVo.getLastName()).firstName(userAccountVo.getFirstName())
                        .username(userAccountVo.getUsername())
                        .password(passwordEncoder.encode(userAccountVo.getPassword())).build());
        MessageResponse messageResponse = new MessageResponse("Sign up successfully!", ResponseStatus.SUCCESS);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(userAccountVo.getUsername()).toUri();
        return ResponseEntity.created(location).body(messageResponse);
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserAccountVo userAccountVo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAccountVo.getUsername(),
                        userAccountVo.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @ResponseBody
    @PostMapping("/isLogin")
    public boolean isLogin(){
        return Boolean.TRUE;
    }
}

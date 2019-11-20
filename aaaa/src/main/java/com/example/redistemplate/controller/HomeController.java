package com.example.redistemplate.controller;

import com.example.redistemplate.controller.vo.UserInfoRequestVo;
import com.example.redistemplate.controller.vo.UserInfoVo;
import com.example.redistemplate.dao.UserDao;
import com.example.redistemplate.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
public class HomeController {

    @Autowired
    private UserDao userDao;

    @ResponseBody
    @PostMapping("/userInfo")
    public UserInfoVo userInfo(@RequestBody UserInfoRequestVo userInfoRequest) {
        User user = userDao.findByUsername(userInfoRequest.getUsername());
        if(Objects.isNull(user)){
            return null;
        }
        return UserInfoVo.builder().userName(user.getLastName()).build();
    }
}

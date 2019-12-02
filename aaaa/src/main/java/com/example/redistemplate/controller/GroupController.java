package com.example.redistemplate.controller;

import com.example.redistemplate.controller.vo.GroupRequestVo;

import com.example.redistemplate.controller.vo.GroupVo;
import com.example.redistemplate.service.GroupService;
import com.example.redistemplate.service.bo.GroupBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Objects;

@Controller
public class GroupController {
    @Autowired
    private GroupService service;

    @ResponseBody
    @PostMapping("/createGroup")
    public GroupVo userInfo(@RequestBody GroupRequestVo groupRequestVo) {
        GroupBo bo = service.createGroup(groupRequestVo.getMembers(), groupRequestVo.getGroupName());
        return GroupVo.builder().groupId(bo.getGroupId()).build();
    }
}

package com.example.redistemplate.controller;

import com.example.redistemplate.controller.vo.GroupRequestVo;
import com.example.redistemplate.controller.vo.GroupResponseVo;
import com.example.redistemplate.controller.vo.GroupVo;
import com.example.redistemplate.controller.vo.LoadGroupsRequestVo;
import com.example.redistemplate.service.GroupService;
import com.example.redistemplate.service.bo.GroupBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/createGroup")
    @ResponseBody
    public GroupResponseVo createGroup(@RequestBody GroupRequestVo vo){
        GroupBo bo = groupService.createGroup(vo.getGroupName(), vo.getMembers());
        return GroupResponseVo.builder().groupId(bo.getGroupId()).build();
    }

}

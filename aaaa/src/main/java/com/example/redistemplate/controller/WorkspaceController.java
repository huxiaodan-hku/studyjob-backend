package com.example.redistemplate.controller;

import com.example.redistemplate.controller.vo.*;
import com.example.redistemplate.entities.WorkSpace;
import com.example.redistemplate.service.WorkSpaceService;
import com.example.redistemplate.service.bo.WorkSpaceSearchResultBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WorkspaceController {

    @Autowired
    private WorkSpaceService service;
    @ResponseBody
    @PostMapping("/createWorkspace")
    public WorkspaceResponseVo createWorkspace(@RequestBody WorkspaceRequestVo workspaceRequestVo) {
        WorkSpace bo = service.createNewWorkSpace(workspaceRequestVo.getUserAccount(), workspaceRequestVo.getWorkspaceName());
        return WorkspaceResponseVo.builder().workspaceId(bo.getId()).workspaceName(bo.getName()).build();
    }

    @ResponseBody
    @PostMapping("/searchWorkSpace")
    public WorkSpaceSearchResultBo searchWorkSpace(@RequestBody WorkspaceSearchVo workspaceSearchVo) {
        return service.search(workspaceSearchVo.getToken(), workspaceSearchVo.getCursor(), 5);
    }


}

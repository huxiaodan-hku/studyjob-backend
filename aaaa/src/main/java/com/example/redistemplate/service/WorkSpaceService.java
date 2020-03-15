package com.example.redistemplate.service;

import com.example.redistemplate.entities.WorkSpace;
import com.example.redistemplate.service.bo.WorkSpaceSearchBo;
import com.example.redistemplate.service.bo.WorkSpaceSearchResultBo;

import java.util.List;

public interface WorkSpaceService {
    WorkSpace createNewWorkSpace(String userAccount, String workspaceId);
    WorkSpaceSearchResultBo search(String token, String cursor, int limit);
}

package com.example.redistemplate.dao.spec;

import com.example.redistemplate.entities.WorkSpace;
import com.example.redistemplate.service.bo.WorkSpaceSearchBo;

import java.util.List;

public interface WorkSpaceDao {
    List<Long> getWorkSpaceList(String userAccount);

    List<WorkSpace> getWorkSpace(List<Long> workspaceIds);

    void saveWorkSpace(WorkSpace workSpace);

    long generateNewWorkSpaceId();

    void addWorkSpaceMember(long id, String userAccount);

    void addWorkSpaceMembers(long id, List<String> userAccount);

    List<String> getWorkSpaceMembers(long workspaceId);

    WorkSpaceSearchBo searchAllWorkspaceIds(String token, String cursor, int limit);

    long getIdFromHashKey(String hashKey);
}

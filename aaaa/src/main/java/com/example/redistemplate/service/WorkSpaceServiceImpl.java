package com.example.redistemplate.service;

import com.example.redistemplate.dao.spec.WorkSpaceDao;
import com.example.redistemplate.entities.WorkSpace;
import com.example.redistemplate.service.bo.WorkSpaceSearchBo;
import com.example.redistemplate.service.bo.WorkSpaceSearchResultBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkSpaceServiceImpl implements WorkSpaceService {
    @Autowired
    private WorkSpaceDao workSpaceDao;

    @Override
    public WorkSpace createNewWorkSpace(String creator, String workspaceName) {
        long id = workSpaceDao.generateNewWorkSpaceId();

        WorkSpace workSpace = WorkSpace.builder().name(workspaceName).id(id).creator(creator).userAccounts(new ArrayList<>()).build();
        workSpaceDao.saveWorkSpace(workSpace);
        return workSpace;
    }

    @Override
    public WorkSpaceSearchResultBo search(String token, String cursor, int limit ) {
        WorkSpaceSearchBo bo = workSpaceDao.searchAllWorkspaceIds(token, cursor, limit);
        List<Long> ids = bo.getWorkspace().stream().map(key->workSpaceDao.getIdFromHashKey(key)).collect(Collectors.toList());
        return WorkSpaceSearchResultBo.builder().workSpaceList(workSpaceDao.getWorkSpace(ids)).cursor(cursor).build();
    }
}

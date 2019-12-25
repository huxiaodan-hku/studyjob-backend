package com.example.redistemplate.service;

import com.example.redistemplate.dao.spec.WorkSpaceDao;
import com.example.redistemplate.entities.WorkSpace;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class WorkSpaceServiceImpl implements WorkSpaceService {
    @Autowired
    private WorkSpaceDao workSpaceDao;

    @Override
    public WorkSpace createNewWorkSpace(String creator) {
        long id = workSpaceDao.generateNewWorkSpaceId();
        WorkSpace workSpace = WorkSpace.builder().id(id).creator(creator).userAccounts(new ArrayList<>()).build();
        workSpaceDao.saveWorkSpace(workSpace);
    }
}

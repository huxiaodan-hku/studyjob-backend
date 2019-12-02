package com.example.redistemplate.service;

import com.example.redistemplate.dao.GroupDao;
import com.example.redistemplate.entities.GroupDto;
import com.example.redistemplate.service.bo.GroupBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override public GroupBo createGroup(List<String> users, String groupName) {
        GroupDto group = groupDao.saveGroup(users, groupName);
        return GroupBo.builder().groupId(group.getGroupId()).createTime(group.getCreateTime())
                .groupName(group.getGroupName()).members(group.getMembers()).build();

    }
}

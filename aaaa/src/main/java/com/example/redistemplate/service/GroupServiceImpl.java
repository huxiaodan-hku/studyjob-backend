package com.example.redistemplate.service;

import com.example.redistemplate.dao.spec.GroupDao;
import com.example.redistemplate.entities.GroupDto;
import com.example.redistemplate.service.bo.GroupBo;
import com.example.redistemplate.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public GroupBo createGroup(String groupName, List<String> members) {
        GroupDto dto = GroupDto.builder()
                .groupId(groupDao.generateGroupId())
                .createTime(TimeUtil.getCurrentTime())
                .groupName(groupName)
                .members(members)
                .build();
        groupDao.createGroup(dto);
        return GroupBo.builder()
                .createTime(dto.getCreateTime())
                .groupId(dto.getGroupId())
                .groupName(dto.getGroupName())
                .members(dto.getMembers())
                .build();
    }

}

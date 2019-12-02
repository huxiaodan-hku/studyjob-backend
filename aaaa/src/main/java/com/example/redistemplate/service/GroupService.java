package com.example.redistemplate.service;

import com.example.redistemplate.service.bo.GroupBo;

import java.util.List;

public interface GroupService {

    public GroupBo createGroup(List<String> users, String groupName);
}

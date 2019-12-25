package com.example.redistemplate.service;

import com.example.redistemplate.service.bo.GroupBo;

import java.util.List;

public interface GroupService {
    GroupBo createGroup(String groupName, List<String> members);
}

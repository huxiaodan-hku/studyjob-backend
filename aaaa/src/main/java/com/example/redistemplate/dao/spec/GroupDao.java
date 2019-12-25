package com.example.redistemplate.dao.spec;

import com.example.redistemplate.entities.GroupDto;

public interface GroupDao {
    boolean createGroup(GroupDto groupDto);
    long generateGroupId();
}

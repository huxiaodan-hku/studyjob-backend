package com.example.redistemplate.dao;

import com.example.redistemplate.entities.GroupDto;

import java.util.List;

public interface GroupDao {
    GroupDto saveGroup(List<String> users, String groupName);
}

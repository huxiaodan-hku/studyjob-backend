package com.example.redistemplate.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupDto {

    private long groupId;
    private List<String> members;
    private String groupName;
    private String createTime;
}

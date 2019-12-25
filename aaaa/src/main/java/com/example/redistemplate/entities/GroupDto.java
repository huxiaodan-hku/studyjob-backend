package com.example.redistemplate.entities;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GroupDto {
   private long groupId;
   private String groupName;
   private List<String> members;
   private String createTime;
}

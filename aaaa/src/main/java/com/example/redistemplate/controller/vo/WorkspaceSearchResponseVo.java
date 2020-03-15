package com.example.redistemplate.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceSearchResponseVo {
    List<WorkspaceResponseVo> workspace;
    String cursor;
}

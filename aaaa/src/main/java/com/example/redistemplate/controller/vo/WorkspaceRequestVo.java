package com.example.redistemplate.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceRequestVo {
    String userAccount;
    String workspaceName;
}

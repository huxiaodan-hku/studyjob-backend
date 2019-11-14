package com.example.redistemplate.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private long id;
    private RoleName roleName;
}

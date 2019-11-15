package com.example.redistemplate.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class User implements Serializable {
    public long userId;
    public String username;
    public String password;
    private Set<Role> roles = new HashSet<>();
    public String lastName;
    public String firstName;
}

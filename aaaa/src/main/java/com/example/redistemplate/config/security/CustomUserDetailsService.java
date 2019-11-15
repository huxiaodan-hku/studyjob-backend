package com.example.redistemplate.config.security;

import com.example.redistemplate.dao.UserDao;
import com.example.redistemplate.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Let people login with either username or email
        User user = userDao.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found with username or email : " + username);
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        user.getRoles().stream()
                .forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority((role.getRoleName().name()))));
        return UserPrincipal.create(user);

    }
}
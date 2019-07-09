/*
 * Copyright (c) 2019 LINE Corporation. All rights reserved.
 * LINE Corporation PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package pl.bmstefanski.website.auth;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import pl.bmstefanski.website.model.Role;

@Service
public class MyUserDetails implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username){
        return User//
                   .withUsername(username)//
                   .password("")
                   .authorities(Role.ROLE_USER)//
                   .accountExpired(false)//
                   .accountLocked(false)//
                   .credentialsExpired(false)//
                   .disabled(false)//
                   .build();
    }
}

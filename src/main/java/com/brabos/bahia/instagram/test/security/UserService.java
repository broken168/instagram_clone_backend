package com.brabos.bahia.instagram.test.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static UserSS getAuthenticatedUser(){
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }
}

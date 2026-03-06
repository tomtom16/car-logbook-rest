package com.wiensquare.car.service.impl;

import com.wiensquare.car.dto.User;
import com.wiensquare.car.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultUserService implements UserService {

    @Override
    public User getCurrentUser() {
        User user = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
            String username = jwt.getSubject();
            UUID uid = UUID.fromString(jwt.getClaimAsString("uid"));
            user = new User();
            user.setUid(uid);
            user.setUsername(username);
        }

        return user;
    }

}

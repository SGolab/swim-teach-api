package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.UserDetailsDto;
import com.golab.swimteach.mapper.UserDetailsMapper;
import com.golab.swimteach.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserDetailsController {

    private final UserDetailsService userDetailsService;
    private final UserDetailsMapper userDetailsMapper = UserDetailsMapper.getInstance();

    public UserDetailsController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/users/{userName}/details")
    public UserDetailsDto getUserDetails(@AuthenticationPrincipal User user, @PathVariable String userName) throws Exception {

        if (!Objects.equals(user.getUsername(), userName)) {
            throw new Exception("User with username: " + userName + " was not found"); //todo exceptions
        }

        return userDetailsMapper.toUserDetailsDto(user);
    }
}

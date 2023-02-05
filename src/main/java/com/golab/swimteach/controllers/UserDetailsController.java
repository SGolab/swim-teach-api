package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.UserDetailsDto;
import com.golab.swimteach.mapper.UserDetailsMapper;
import com.golab.swimteach.model.User;
import com.golab.swimteach.repositories.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDetailsController {

    private final UserRepository userRepository;
    private final UserDetailsMapper userDetailsMapper = UserDetailsMapper.getInstance();

    public UserDetailsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/userDetails")
    public UserDetailsDto getUserDetails(@AuthenticationPrincipal User user) throws Exception {
        return userDetailsMapper.toUserDetailsDto(user);
    }

    @GetMapping("/users/{userId}/userDetails")
    public UserDetailsDto getUserDetails(@PathVariable Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id: " + userId + " was not found."));

        return userDetailsMapper.toUserDetailsDto(user);
    }
}

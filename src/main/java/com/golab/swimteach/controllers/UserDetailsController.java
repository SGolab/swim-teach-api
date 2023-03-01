package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.ChangePasswordDto;
import com.golab.swimteach.dto.UserDetailsDto;
import com.golab.swimteach.mapper.UserDetailsMapper;
import com.golab.swimteach.model.User;
import com.golab.swimteach.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserDetailsController {

    private final UserService userService;
    private final UserDetailsMapper userDetailsMapper = UserDetailsMapper.getInstance();

    public UserDetailsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userDetails")
    public UserDetailsDto getUserDetails(@AuthenticationPrincipal User user) throws Exception {
        return userDetailsMapper.toUserDetailsDto(user);
    }

    @GetMapping("/users/{userId}/userDetails")
    public UserDetailsDto getUserDetails(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("/changePassword") //todo move to security/loginController?
    public UserDetailsDto postChangePassword(@AuthenticationPrincipal User user, @RequestBody ChangePasswordDto changePasswordDto) {
        return userService.changePassword(user, changePasswordDto);
    }
}

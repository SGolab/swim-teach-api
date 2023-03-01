package com.golab.swimteach.services;

import com.golab.swimteach.dto.ChangePasswordDto;
import com.golab.swimteach.dto.UserDetailsDto;
import com.golab.swimteach.model.User;

import java.util.Optional;

public interface UserService {
    UserDetailsDto changePassword(User user, ChangePasswordDto changePasswordDto);

    UserDetailsDto findById(Long userId);
}

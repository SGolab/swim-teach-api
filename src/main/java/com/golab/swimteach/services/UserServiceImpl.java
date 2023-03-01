package com.golab.swimteach.services;

import com.golab.swimteach.dto.ChangePasswordDto;
import com.golab.swimteach.dto.UserDetailsDto;
import com.golab.swimteach.mapper.UserDetailsMapper;
import com.golab.swimteach.model.User;
import com.golab.swimteach.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserDetailsMapper userDetailsMapper = UserDetailsMapper.getInstance();

    private final String AUTH_ENCRYPTION;

    public UserServiceImpl(UserRepository userRepository, @Value("${auth.encryption}") String AUTH_ENCRYPTION) {
        this.userRepository = userRepository;
        this.AUTH_ENCRYPTION = AUTH_ENCRYPTION;
    }

    @Override
    public UserDetailsDto changePassword(User user, ChangePasswordDto changePasswordDto) {

        user.setPassword(AUTH_ENCRYPTION + changePasswordDto.getNewPassword());
        User savedUser = userRepository.save(user);

        return userDetailsMapper.toUserDetailsDto(savedUser);
    }

    @Override
    public UserDetailsDto findById(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User with id: " + userId + " was not found."));

        return userDetailsMapper.toUserDetailsDto(user);
    }
}

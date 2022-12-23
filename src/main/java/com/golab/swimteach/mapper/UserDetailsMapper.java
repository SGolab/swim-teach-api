package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.UserDetailsDto;
import com.golab.swimteach.model.User;

public class UserDetailsMapper {

    private static UserDetailsMapper INSTANCE;

    public static UserDetailsMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDetailsMapper();
        }
        return INSTANCE;
    }

    public UserDetailsDto toUserDetailsDto(User user) {
        UserDetailsDto dto = new UserDetailsDto();

        dto.setUserName(user.getUsername());
        dto.setRoles(user.getRoles());
        if (user.getSwimmer() != null) {
            dto.setFirstName(user.getSwimmer().getFirstName());
            dto.setLastName(user.getSwimmer().getLastName());
        }

        return dto;
    }
}

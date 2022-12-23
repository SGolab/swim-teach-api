package com.golab.swimteach.dto;

import com.golab.swimteach.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class UserDetailsDto {
    private String userName;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
}

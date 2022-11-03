package com.golab.swimteach.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String permission;

    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;
}

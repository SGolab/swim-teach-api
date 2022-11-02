package com.golab.swimteach.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Swimmer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Skill> skillSet = new HashSet<>();

    public Swimmer(String firstName, String lastName, Set<SkillDetails> skillDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skillSet = skillDetails.stream().map(Skill::new).collect(Collectors.toSet());
    }

    public Swimmer() {

    }
}

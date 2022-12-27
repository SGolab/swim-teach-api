package com.golab.swimteach.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Entity
public class Swimmer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Skill> skillSet = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Goal> goalsSet = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<Lesson> lessonSet = new HashSet<>();

    public Swimmer(String firstName, String lastName, Set<SkillDetails> skillDetails, Set<GoalDetails> goalDetails, Set<Lesson> lessons) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skillSet = skillDetails.stream().map(Skill::new).collect(Collectors.toSet());
        this.goalsSet = goalDetails.stream().map(Goal::new).collect(Collectors.toSet());
        this.lessonSet = lessons;
    }

    public Swimmer() {

    }
}

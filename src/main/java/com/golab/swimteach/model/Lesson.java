package com.golab.swimteach.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dateTime;
    private String location;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<SkillMark> skillMarks;
}

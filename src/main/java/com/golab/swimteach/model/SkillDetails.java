package com.golab.swimteach.model;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class SkillDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Lob
    private String description;

    public SkillDetails(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public SkillDetails() {

    }
}

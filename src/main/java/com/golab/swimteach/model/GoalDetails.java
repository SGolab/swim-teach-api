package com.golab.swimteach.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class GoalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Lob
    private String description;

    public GoalDetails(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public GoalDetails() {
    }
}

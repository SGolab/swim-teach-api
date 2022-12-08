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
    private Long id;

    private String title;

    @Lob
    private String description;

    public GoalDetails() {
    }
}

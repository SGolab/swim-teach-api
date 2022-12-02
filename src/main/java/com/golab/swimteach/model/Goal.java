package com.golab.swimteach.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private GoalStatus status;

    @OneToOne
    private GoalDetails details;

    public Goal(GoalDetails details) {
        this.status = GoalStatus.NOT_ACHIEVED;
        this.details = details;
    }

    public Goal(GoalDetails details, GoalStatus goalStatus) {
        this.status = goalStatus;
        this.details = details;
    }

    public Goal() {

    }
}

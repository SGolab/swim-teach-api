package com.golab.swimteach.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private SkillDetails skillDetails;

    private SkillStatus status;

    public Skill(SkillDetails skillDetails) {
        this.skillDetails = skillDetails;
        this.status = SkillStatus.NOT_TRAINED;
    }

    public Skill(SkillDetails skillDetails, SkillStatus status) {
        this.skillDetails = skillDetails;
        this.status = status;
    }

    public Skill() {

    }
}

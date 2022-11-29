package com.golab.swimteach.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private SkillDetails skillDetails;

    @Setter
    private SkillStatus status;

    public Skill(SkillDetails skillDetails) {
        this.skillDetails = skillDetails;
        this.status = SkillStatus.NOT_TRAINED;
    }

    public Skill(SkillDetails skillDetails, SkillStatus skillStatus) {
        this.skillDetails = skillDetails;
        this.status = skillStatus;
    }

    public Skill() {

    }
}

package com.golab.swimteach.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private SkillDetails skillDetails;

    private String customTitle;

    private UnitEnum unit;
    private Integer amount;

    public Task(SkillDetails skillDetails, UnitEnum unit, Integer amount) {
        this.skillDetails = skillDetails;
        this.unit = unit;
        this.amount = amount;
    }

    public Task(String customTitle, UnitEnum unit, Integer amount) {
        this.customTitle = customTitle;
        this.unit = unit;
        this.amount = amount;
    }
}

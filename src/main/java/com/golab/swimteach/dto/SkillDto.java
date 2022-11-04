package com.golab.swimteach.dto;

import com.golab.swimteach.model.SkillStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillDto {
    private Long id;
    private SkillStatus status;
    private String title;
}

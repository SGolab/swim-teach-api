package com.golab.swimteach.dto;

import com.golab.swimteach.model.SkillStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillDto {
    private SkillStatus status;
    private SkillDetailsDto details;
}

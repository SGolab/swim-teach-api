package com.golab.swimteach.dto;

import com.golab.swimteach.model.Skill;
import com.golab.swimteach.model.SkillStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
public class ProgressTreeDto {
    List<SkillDto> skills;
}


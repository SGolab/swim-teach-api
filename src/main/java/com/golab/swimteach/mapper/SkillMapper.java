package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.SkillDto;
import com.golab.swimteach.model.Skill;

public class SkillMapper {

    private static SkillMapper INSTANCE;

    public static SkillMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SkillMapper();
        }
        return INSTANCE;
    }

    public SkillDto toSkillDto(Skill skill) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getId());
        skillDto.setStatus(skill.getStatus());
        skillDto.setTitle(skill.getSkillDetails().getTitle());
        return skillDto;
    }
}

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

        skillDto.setStatus(skill.getStatus());
        skillDto.setDetailsId(skill.getSkillDetails().getId());

        skillDto.setTitle(skill.getSkillDetails().getTitle());
        skillDto.setStageTitle(skill.getSkillDetails().getStageTitle());
        skillDto.setSubjectTitle(skill.getSkillDetails().getSubjectTitle());

        skillDto.setDescription(skill.getSkillDetails().getDescription());
        skillDto.setUrl(skill.getSkillDetails().getUrl());
        return skillDto;
    }
}

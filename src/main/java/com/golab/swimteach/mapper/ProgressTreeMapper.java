package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.ProgressTreeDto;
import com.golab.swimteach.dto.SkillDto;
import com.golab.swimteach.model.Skill;

import java.util.List;
import java.util.stream.Collectors;

public class ProgressTreeMapper {

    private static ProgressTreeMapper INSTANCE = new ProgressTreeMapper();

    private ProgressTreeMapper() {
    }

    public static ProgressTreeMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProgressTreeMapper();
        }
        return INSTANCE;
    }

    public ProgressTreeDto toProgressTree(List<Skill> skills) {

        ProgressTreeDto progressTreeDto = new ProgressTreeDto();

        List<SkillDto> skillDtos = skills
                .stream()
                .map(skill -> SkillMapper.getInstance().toSkillDto(skill))
                .collect(Collectors.toList());

        progressTreeDto.setSkills(skillDtos);
        return progressTreeDto;
    }


}

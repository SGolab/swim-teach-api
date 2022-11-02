package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.ProgressTreeDto;
import com.golab.swimteach.dto.SkillDetailsDto;
import com.golab.swimteach.dto.SkillDto;
import com.golab.swimteach.model.Skill;
import com.golab.swimteach.model.SkillDetails;

import java.util.List;
import java.util.stream.Collectors;

public class ProgressTreeMapper {

    public static ProgressTreeDto toProgressTree(List<Skill> skills) {

        ProgressTreeDto progressTreeDto = new ProgressTreeDto();

        List<SkillDto> skillDtos = skills
                .stream()
                .map(skill -> SkillMapper.getInstance().toSkillDto(skill))
                .collect(Collectors.toList());

        progressTreeDto.setSkills(skillDtos);
        return progressTreeDto;
    }


}

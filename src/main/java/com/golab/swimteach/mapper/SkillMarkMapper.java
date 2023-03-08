package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.SkillMarkDto;
import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.SkillMark;

import java.util.List;

public class SkillMarkMapper {

    private static SkillMarkMapper INSTANCE;

    private SkillMarkMapper() {
    }

    public static SkillMarkMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SkillMarkMapper();
        }
        return INSTANCE;
    }


    public SkillMarkDto toSkillMarkDto(SkillMark skillMark) {
        SkillMarkDto skillMarkDto = new SkillMarkDto();

        skillMarkDto.setDetailsId(skillMark.getSkillDetails().getId());
        skillMarkDto.setTitle(skillMark.getSkillDetails().getTitle());
        skillMarkDto.setStageTitle(skillMark.getSkillDetails().getStageTitle());
        skillMarkDto.setSubjectTitle(skillMark.getSkillDetails().getSubjectTitle());
        skillMarkDto.setUrl(skillMark.getSkillDetails().getUrl());
        skillMarkDto.setStatus(skillMark.getStatus());
        skillMarkDto.setPrevStatus(skillMark.getPrevStatus());

        return skillMarkDto;
    }

    public SkillMark toSkillMark(SkillMarkDto dto, List<SkillDetails> skillDetails) {
        SkillMark skillMark = new SkillMark();

        skillMark.setSkillDetails(
                skillDetails.stream()
                        .filter(sd -> sd.getId().equals(dto.getDetailsId()))
                        .findFirst()
                        .orElseThrow()
        );
        skillMark.setStatus(dto.getStatus());
        skillMark.setPrevStatus(dto.getPrevStatus());

        return skillMark;
    }

}

package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.SkillDetailsDto;
import com.golab.swimteach.model.SkillDetails;

public class SkillDetailsMapper {

    private static SkillDetailsMapper INSTANCE;

    private SkillDetailsMapper() {
    }

    public static SkillDetailsMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SkillDetailsMapper();
        }

        return INSTANCE;
    }

    public SkillDetailsDto toSkillDetailsDto(SkillDetails details) {
        SkillDetailsDto dto = new SkillDetailsDto();
        dto.setId(details.getId());
        dto.setTitle(details.getTitle());
        dto.setDescription(details.getDescription());
        return dto;
    }
}

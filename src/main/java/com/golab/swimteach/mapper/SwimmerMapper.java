package com.golab.swimteach.mapper;

import com.golab.swimteach.model.GoalDetails;
import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.dto.SwimmerDto;

import java.util.Set;

public class SwimmerMapper {

    private static SwimmerMapper INSTANCE;

    public static SwimmerMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SwimmerMapper();
        }
        return INSTANCE;
    }

    private SwimmerMapper() {}

    public SwimmerDto toDto(Swimmer swimmer) {
        SwimmerDto dto = new SwimmerDto();
        dto.setId(swimmer.getId());
        dto.setFirstName(swimmer.getFirstName());
        dto.setLastName(swimmer.getLastName());

        return dto;
    }

    public Swimmer toSwimmer(SwimmerDto dto, Set<SkillDetails> skillDetails, Set<GoalDetails> goalDetails) {
        Swimmer swimmer = new Swimmer(skillDetails, goalDetails);
        swimmer.setFirstName(dto.getFirstName());
        swimmer.setLastName(dto.getLastName());
        return swimmer;
    }

}

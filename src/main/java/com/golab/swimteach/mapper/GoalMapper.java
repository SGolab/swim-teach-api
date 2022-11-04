package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.GoalDto;
import com.golab.swimteach.model.Goal;

public class GoalMapper {

    private static GoalMapper INSTANCE;

    private GoalMapper() {
    }

    public static GoalMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GoalMapper();
        }
        return INSTANCE;
    }

    public GoalDto toGoalDto(Goal goal) {
        GoalDto goalDto = new GoalDto();
        goalDto.setId(goal.getId());
        goalDto.setStatus(goal.getStatus());
        goalDto.setTitle(goal.getDetails().getTitle());
        goalDto.setDescription(goal.getDetails().getDescription());

        return goalDto;
    }
}

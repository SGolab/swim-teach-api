package com.golab.swimteach.services;

import com.golab.swimteach.dto.GoalDto;
import com.golab.swimteach.dto.GoalsListDto;

import java.util.List;

public interface GoalService {
    GoalsListDto getGoalList(Long swimmerId);

    GoalsListDto getGoalList();

    GoalDto updateGoalStatus(Long goalId, GoalDto goalDto);
}

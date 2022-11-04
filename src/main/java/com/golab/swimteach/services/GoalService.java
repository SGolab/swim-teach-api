package com.golab.swimteach.services;

import com.golab.swimteach.dto.GoalDto;

import java.util.List;

public interface GoalService {


    List<GoalDto> getGoalList(Long swimmerId);

    List<GoalDto> getGoalList();

    GoalDto updateGoalStatus(Long goalId, GoalDto goalDto);
}

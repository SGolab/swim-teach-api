package com.golab.swimteach.dto;

import com.golab.swimteach.mapper.GoalMapper;
import com.golab.swimteach.model.Goal;
import com.golab.swimteach.model.GoalDetails;
import com.golab.swimteach.model.GoalStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GoalsListFactory {

    private static final GoalMapper goalMapper = GoalMapper.getInstance();

    public GoalsListFactory() {
    }

    public static GoalsListDto createGoalsList(GoalsTemplate goalsTemplate, Collection<Goal> goals) {
        GoalsListDto goalsListDto = new GoalsListDto();
        goalsListDto.setGoals(createGoals(goalsTemplate.getGoals(), goals));
        return goalsListDto;
    }

    private static List<GoalDto> createGoals(List<GoalDetails> goalDetailsList, Collection<Goal> goals) {

        List<Goal> result = new ArrayList<>();

        goalDetailsList.forEach(goalDetails -> {

            Goal goal = goals.stream()
                    .filter(g -> g.getDetails().getId().equals(goalDetails.getId()))
                    .findFirst()
                    .orElseThrow();

            result.add(goal);
        });

        return result.stream().map(goalMapper::toGoalDto).collect(Collectors.toList());
    }

    public static GoalsListDto createGoalsList(GoalsTemplate goalsTemplate) {
        GoalsListDto goalsListDto = new GoalsListDto();
        goalsListDto.setGoals(createGoals(goalsTemplate.getGoals()));
        return goalsListDto;
    }

    private static List<GoalDto> createGoals(List<GoalDetails> goalDetailsList) {
        return goalDetailsList.stream()
                .map(gd -> new Goal(gd, GoalStatus.ACHIEVED))
                .map(goalMapper::toGoalDto)
                .collect(Collectors.toList());
    }
}

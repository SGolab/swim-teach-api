package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.GoalDto;
import com.golab.swimteach.dto.GoalsListDto;
import com.golab.swimteach.model.User;
import com.golab.swimteach.services.GoalService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/goals")
    public GoalsListDto getGoalList(@AuthenticationPrincipal User user) {
        if (user.getSwimmer() == null) {
            return goalService.getGoalList();
        } else {
            return goalService.getGoalList(user.getSwimmer().getId());
        }
    }

    @PutMapping("/goals/{goalId}/updateStatus")
    public GoalDto updateGoalStatus(@PathVariable Long goalId, @RequestBody GoalDto goalDto) {
        return goalService.updateGoalStatus(goalId, goalDto);
    }

    @GetMapping("/swimmers/{swimmerId}/goals")
    public GoalsListDto getGoalListBySwimmerId(@PathVariable Long swimmerId) {
        return goalService.getGoalList(swimmerId);
    }
}

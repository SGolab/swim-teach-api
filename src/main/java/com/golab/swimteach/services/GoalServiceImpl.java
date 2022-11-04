package com.golab.swimteach.services;

import com.golab.swimteach.dto.GoalDto;
import com.golab.swimteach.mapper.GoalMapper;
import com.golab.swimteach.model.Goal;
import com.golab.swimteach.model.GoalDetails;
import com.golab.swimteach.model.GoalStatus;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.GoalDetailsRepository;
import com.golab.swimteach.repositories.GoalRepository;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    GoalMapper goalMapper = GoalMapper.getInstance();

    private final SwimmerRepository swimmerRepository;
    private final GoalDetailsRepository goalDetailsRepository;
    private final GoalRepository goalRepository;

    public GoalServiceImpl(SwimmerRepository swimmerRepository, GoalDetailsRepository goalDetailsRepository, GoalRepository goalRepository) {
        this.swimmerRepository = swimmerRepository;
        this.goalDetailsRepository = goalDetailsRepository;
        this.goalRepository = goalRepository;
    }

    @Override
    public List<GoalDto> getGoalList(Long swimmerId) {
        Swimmer swimmer = swimmerRepository.findById(swimmerId)
                .orElseThrow(() -> new RuntimeException("Swimmer Not Found")); //todo exceptions

        return swimmer.getGoalSet().stream().map(goalMapper::toGoalDto).toList();
    }

    @Override
    public List<GoalDto> getGoalList() {
        List<GoalDetails> goalDetails = goalDetailsRepository.findAll();

        List<Goal> goals = goalDetails.stream().map(gd -> {
            Goal goal = new Goal();
            goal.setDetails(gd);
            goal.setStatus(GoalStatus.ACHIEVED);
            return goal;
        }).toList();

        return goals.stream().map(goalMapper::toGoalDto).toList();
    }

    @Override
    public GoalDto updateGoalStatus(Long goalId, GoalDto goalDto) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal Not Found"));//todo exceptions

        goal.setStatus(goalDto.getStatus());

        return goalMapper.toGoalDto(goalRepository.save(goal));
    }
}

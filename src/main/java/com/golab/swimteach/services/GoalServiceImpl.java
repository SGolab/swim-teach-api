package com.golab.swimteach.services;

import com.golab.swimteach.dto.GoalDto;
import com.golab.swimteach.dto.GoalsListDto;
import com.golab.swimteach.dto.GoalsListFactory;
import com.golab.swimteach.dto.GoalsTemplate;
import com.golab.swimteach.mapper.GoalMapper;
import com.golab.swimteach.model.Goal;
import com.golab.swimteach.model.GoalDetails;
import com.golab.swimteach.model.GoalStatus;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.GoalDetailsRepository;
import com.golab.swimteach.repositories.GoalRepository;
import com.golab.swimteach.repositories.SwimmerRepository;
import com.golab.swimteach.repositories.TemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    GoalMapper goalMapper = GoalMapper.getInstance();

    private final SwimmerRepository swimmerRepository;
    private final GoalDetailsRepository goalDetailsRepository;
    private final GoalRepository goalRepository;

    private final TemplateRepository templateRepository;

    public GoalServiceImpl(SwimmerRepository swimmerRepository, GoalDetailsRepository goalDetailsRepository, GoalRepository goalRepository, TemplateRepository templateRepository) {
        this.swimmerRepository = swimmerRepository;
        this.goalDetailsRepository = goalDetailsRepository;
        this.goalRepository = goalRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public GoalsListDto getGoalList(Long swimmerId) {
        Swimmer swimmer = swimmerRepository.findById(swimmerId)
                .orElseThrow(() -> new RuntimeException("Swimmer Not Found")); //todo exceptions

        GoalsTemplate goalTemplate = templateRepository.getGoalTemplate();

        GoalsListDto goalsListDto = GoalsListFactory.createGoalsList(goalTemplate, swimmer.getGoalsSet());

        return goalsListDto;
    }

    @Override
    public GoalsListDto getGoalList() {
        List<GoalDetails> goalDetails = goalDetailsRepository.findAll();

        GoalsTemplate goalTemplate = templateRepository.getGoalTemplate();

        GoalsListDto goalsListDto = GoalsListFactory.createGoalsList(goalTemplate);

        return goalsListDto;
    }

    @Override
    public GoalDto updateGoalStatus(Long goalId, GoalDto goalDto) {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal Not Found"));//todo exceptions

        goal.setStatus(goalDto.getStatus());

        return goalMapper.toGoalDto(goalRepository.save(goal));
    }
}

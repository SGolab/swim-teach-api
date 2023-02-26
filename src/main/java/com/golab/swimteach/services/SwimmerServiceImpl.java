package com.golab.swimteach.services;

import com.golab.swimteach.mapper.SwimmerMapper;
import com.golab.swimteach.dto.SwimmerDto;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.GoalDetailsRepository;
import com.golab.swimteach.repositories.SkillDetailsRepository;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class SwimmerServiceImpl implements SwimmerService {

    private final SwimmerRepository swimmerRepository;

    private final SkillDetailsRepository skillDetailsRepository;

    private final GoalDetailsRepository goalDetailsRepository;

    private final SwimmerMapper swimmerMapper = SwimmerMapper.getInstance();

    public SwimmerServiceImpl(SwimmerRepository swimmerRepository, SkillDetailsRepository skillDetailsRepository, GoalDetailsRepository goalDetailsRepository) {
        this.swimmerRepository = swimmerRepository;
        this.skillDetailsRepository = skillDetailsRepository;
        this.goalDetailsRepository = goalDetailsRepository;
    }

    @Override
    public List<SwimmerDto> getAllSwimmers() {
        return swimmerRepository
                .findAll()
                .stream()
                .map(swimmerMapper::toDto)
                .toList();
    }

    @Override
    public SwimmerDto getSwimmerInfo(Long swimmerId) {
        Swimmer foundSwimmer = swimmerRepository.findById(swimmerId).orElseThrow(); //todo exceptions
        return swimmerMapper.toDto(foundSwimmer);
    }

    @Override
    public SwimmerDto createNewSwimmer(SwimmerDto dto) {

        Swimmer swimmer = swimmerMapper.toSwimmer(dto,
                new HashSet<>(skillDetailsRepository.findAll()),
                new HashSet<>(goalDetailsRepository.findAll())
        );

        Swimmer savedSwimmer = swimmerRepository.save(swimmer);

        return swimmerMapper.toDto(savedSwimmer);
    }
}

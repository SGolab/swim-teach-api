package com.golab.swimteach.services;

import com.golab.swimteach.dto.ProgressTreeDto;
import com.golab.swimteach.mapper.ProgressTreeMapper;
import com.golab.swimteach.model.Skill;
import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.SkillStatus;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.SkillDetailsRepository;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressTreeServiceImpl implements ProgressTreeService{
    private final SwimmerRepository swimmerRepository;
    private final SkillDetailsRepository skillDetailsRepository;

    private final ProgressTreeMapper mapper = ProgressTreeMapper.getInstance();
    public ProgressTreeServiceImpl(SwimmerRepository swimmerRepository, SkillDetailsRepository skillDetailsRepository) {
        this.swimmerRepository = swimmerRepository;
        this.skillDetailsRepository = skillDetailsRepository;
    }

    @Override
    public ProgressTreeDto getProgressTreeAllUnlocked(Long swimmerId) {
        Swimmer swimmer = swimmerRepository.findById(swimmerId)
                .orElseThrow(() -> new RuntimeException("Swimmer Not Found")); //todo exceptions
        return mapper.toProgressTree(new ArrayList<>(swimmer.getSkillSet()));
    }

    @Override
    public ProgressTreeDto getProgressTreeAllUnlocked() {
        List<SkillDetails> skillDetails = skillDetailsRepository.findAll();

        List<Skill> skills = skillDetails.stream()
                .map(sd -> new Skill(sd, SkillStatus.ACQUIRED))
                .collect(Collectors.toList());

        return mapper.toProgressTree(skills);
    }
}

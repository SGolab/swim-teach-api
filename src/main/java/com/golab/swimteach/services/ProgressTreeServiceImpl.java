package com.golab.swimteach.services;

import com.golab.swimteach.dto.ProgressTreeDto;
import com.golab.swimteach.dto.SkillDto;
import com.golab.swimteach.mapper.ProgressTreeMapper;
import com.golab.swimteach.mapper.SkillMapper;
import com.golab.swimteach.model.Skill;
import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.SkillStatus;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.SkillDetailsRepository;
import com.golab.swimteach.repositories.SkillRepository;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressTreeServiceImpl implements ProgressTreeService {
    private final SwimmerRepository swimmerRepository;
    private final SkillRepository skillRepository;
    private final SkillDetailsRepository skillDetailsRepository;

    private final ProgressTreeMapper progressTreeMapper = ProgressTreeMapper.getInstance();
    private final SkillMapper skillMapper = SkillMapper.getInstance();

    public ProgressTreeServiceImpl(SwimmerRepository swimmerRepository, SkillRepository skillRepository, SkillDetailsRepository skillDetailsRepository) {
        this.swimmerRepository = swimmerRepository;
        this.skillRepository = skillRepository;
        this.skillDetailsRepository = skillDetailsRepository;
    }

    @Override
    public ProgressTreeDto getProgressTree(Long swimmerId) {
        Swimmer swimmer = swimmerRepository.findById(swimmerId)
                .orElseThrow(() -> new RuntimeException("Swimmer Not Found")); //todo exceptions
        return progressTreeMapper.toProgressTree(new ArrayList<>(swimmer.getSkillSet()));
    }

    @Override
    public ProgressTreeDto getProgressTreeAllUnlocked() {
        List<SkillDetails> skillDetails = skillDetailsRepository.findAll();

        List<Skill> skills = skillDetails.stream()
                .map(sd -> new Skill(sd, SkillStatus.ACQUIRED))
                .collect(Collectors.toList());

        return progressTreeMapper.toProgressTree(skills);
    }

    @Override
    public SkillDto updateSkillStatus(Long skillId, SkillDto skillDto) {

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill Not Found"));

        skill.setStatus(skillDto.getStatus());
        Skill savedSkill = skillRepository.save(skill);

        return skillMapper.toSkillDto(savedSkill);
    }
}

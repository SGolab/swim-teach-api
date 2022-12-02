package com.golab.swimteach.services;

import com.golab.swimteach.dto.ProgressTreeDto;
import com.golab.swimteach.dto.ProgressTreeFactory;
import com.golab.swimteach.dto.ProgressTreeTemplate;
import com.golab.swimteach.dto.SkillDto;
import com.golab.swimteach.mapper.SkillMapper;
import com.golab.swimteach.model.Skill;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.TemplateRepository;
import com.golab.swimteach.repositories.SkillRepository;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProgressTreeServiceImpl implements ProgressTreeService {
    private final SwimmerRepository swimmerRepository;
    private final SkillRepository skillRepository;
    private final TemplateRepository progressTreeTemplateRepository;
    private final SkillMapper skillMapper = SkillMapper.getInstance();

    public ProgressTreeServiceImpl(SwimmerRepository swimmerRepository, SkillRepository skillRepository, TemplateRepository progressTreeTemplateRepository) {
        this.swimmerRepository = swimmerRepository;
        this.skillRepository = skillRepository;
        this.progressTreeTemplateRepository = progressTreeTemplateRepository;
    }

    @Override
    public ProgressTreeDto getProgressTree(Long swimmerId) {
        Swimmer swimmer = swimmerRepository.findById(swimmerId)
                .orElseThrow(() -> new RuntimeException("Swimmer Not Found")); //todo exceptions

        ProgressTreeTemplate template = progressTreeTemplateRepository.getTreeTemplate();

        return ProgressTreeFactory.createProgressTree(template, new ArrayList<>(swimmer.getSkillSet()), new ArrayList<>(swimmer.getGoalsSet()));
    }

    @Override
    public ProgressTreeDto getProgressTreeAllUnlocked() {
        ProgressTreeTemplate template = progressTreeTemplateRepository.getTreeTemplate();

        return ProgressTreeFactory.createProgressTreeAllUnlocked(template);
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

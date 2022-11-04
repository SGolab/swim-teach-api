package com.golab.swimteach.services;

import com.golab.swimteach.dto.ProgressTreeDto;
import com.golab.swimteach.dto.SkillDto;

public interface ProgressTreeService {
    ProgressTreeDto getProgressTree(Long swimmerId);

    ProgressTreeDto getProgressTreeAllUnlocked();

    SkillDto updateSkillStatus(Long skillId, SkillDto skillDto);
}

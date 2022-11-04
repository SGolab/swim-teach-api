package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.ProgressTreeDto;
import com.golab.swimteach.dto.SkillDto;
import com.golab.swimteach.model.User;
import com.golab.swimteach.services.ProgressTreeServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProgressTreeController {

    private final ProgressTreeServiceImpl progressTreeService;

    public ProgressTreeController(ProgressTreeServiceImpl progressTreeService) {
        this.progressTreeService = progressTreeService;
    }

    @GetMapping("/progressTree")
    public ProgressTreeDto getProgressTree(@AuthenticationPrincipal User user) {

        ProgressTreeDto progressTreeDto;

        if (user.getSwimmer() == null) {
            progressTreeDto = progressTreeService.getProgressTreeAllUnlocked();
        } else {
            progressTreeDto = progressTreeService.getProgressTree(user.getSwimmer().getId());
        }

        return progressTreeDto;
    }

    @GetMapping("swimmers/{swimmerId}/progressTree")
    public ProgressTreeDto getProgressTreeBySwimmerId(@PathVariable Long swimmerId) {
        return progressTreeService.getProgressTree(swimmerId);
    }

    @PutMapping("/skills/{skillId}/updateStatus")
    public SkillDto updateSkillStatus(@PathVariable Long skillId, @RequestBody SkillDto skillDto) {
        return progressTreeService.updateSkillStatus(skillId, skillDto);
    }
}

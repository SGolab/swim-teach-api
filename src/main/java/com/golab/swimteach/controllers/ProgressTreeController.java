package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.ProgressTreeDto;
import com.golab.swimteach.model.User;
import com.golab.swimteach.services.ProgressTreeServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/progressTree")
@RestController
public class ProgressTreeController {

    private final ProgressTreeServiceImpl progressTreeService;

    public ProgressTreeController(ProgressTreeServiceImpl progressTreeService) {
        this.progressTreeService = progressTreeService;
    }

    @GetMapping("")
    public ProgressTreeDto getProgressTree(@AuthenticationPrincipal User user) {

        ProgressTreeDto progressTreeDto;

        if (user.getSwimmer() == null) {
            progressTreeDto = progressTreeService.getProgressTree();
        } else {
            progressTreeDto = progressTreeService.getProgressTree(user.getSwimmer().getId());
        }

        return progressTreeDto;
    }
}

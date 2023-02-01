package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.HomeworkListDto;
import com.golab.swimteach.model.User;
import com.golab.swimteach.services.HomeworkService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeworkController {

    private final HomeworkService homeworkService;

    public HomeworkController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

    @GetMapping("/homework")
    public HomeworkListDto getHomeworkList(@AuthenticationPrincipal User user) {
        if (user.getSwimmer() == null) {
            return homeworkService.getHomeworkList();
        } else {
            return homeworkService.getHomeworkList(user.getSwimmer().getId());
        }
    }

}

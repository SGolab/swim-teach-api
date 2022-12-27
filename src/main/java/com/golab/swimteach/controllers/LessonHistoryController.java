package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.LessonHistoryDto;
import com.golab.swimteach.model.User;
import com.golab.swimteach.services.LessonHistoryService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessonHistoryController {

    private final LessonHistoryService lessonHistoryService;

    public LessonHistoryController(LessonHistoryService lessonHistoryService) {
        this.lessonHistoryService = lessonHistoryService;
    }

    @GetMapping("/lessonHistory")
    public LessonHistoryDto getLessonHistory(@AuthenticationPrincipal User user) {

        if (user.getSwimmer() == null) {
            return lessonHistoryService.getLessonHistory();
        } else {
            return lessonHistoryService.getLessonHistory(user.getSwimmer().getId());
        }
    }
}

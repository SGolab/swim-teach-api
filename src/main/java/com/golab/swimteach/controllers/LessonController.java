package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.LessonDto;
import com.golab.swimteach.dto.LessonHistoryDto;
import com.golab.swimteach.model.User;
import com.golab.swimteach.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonHistoryService) {
        this.lessonService = lessonHistoryService;
    }

    @GetMapping("/lessonHistory")
    public LessonHistoryDto getLessonHistory(@AuthenticationPrincipal User user) {
        if (user.getSwimmer() == null) {
            return lessonService.getLessonHistory();
        } else {
            return lessonService.getLessonHistory(user.getSwimmer().getId());
        }
    }

    @PostMapping ("/swimmers/{swimmerId}/createLesson")
    @ResponseStatus(HttpStatus.CREATED)
    public LessonDto createLesson(@PathVariable Long swimmerId, @RequestBody LessonDto lessonDto) {
        return lessonService.createLesson(swimmerId, lessonDto);
    }
}

package com.golab.swimteach.services;

import com.golab.swimteach.dto.LessonDto;
import com.golab.swimteach.dto.LessonHistoryDto;

public interface LessonService {
    LessonHistoryDto getLessonHistory();
    LessonHistoryDto getLessonHistory(Long swimmerId);
    LessonDto createLesson(Long swimmerId, LessonDto lessonDto);
}

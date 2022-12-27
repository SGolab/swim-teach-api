package com.golab.swimteach.services;

import com.golab.swimteach.dto.LessonHistoryDto;

public interface LessonHistoryService {
    LessonHistoryDto getLessonHistory();
    LessonHistoryDto getLessonHistory(Long swimmerId);
}

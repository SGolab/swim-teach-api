package com.golab.swimteach.services;

import com.golab.swimteach.dto.HomeworkListDto;
import com.golab.swimteach.dto.LessonDto;
import com.golab.swimteach.dto.LessonHistoryDto;
import com.golab.swimteach.model.Homework;

import static com.golab.swimteach.dto.HomeworkListDto.*;

public interface HomeworkService {

    HomeworkListDto getHomeworkList();
    HomeworkListDto getHomeworkList(Long swimmerId);
    HomeworkItemDto createHomework(Long swimmerId, HomeworkItemDto homeworkItemDto);
}

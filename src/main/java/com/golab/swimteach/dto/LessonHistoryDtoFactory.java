package com.golab.swimteach.dto;

import com.golab.swimteach.mapper.LessonMapper;
import com.golab.swimteach.model.Lesson;

import java.util.List;

public class LessonHistoryDtoFactory {

    private static LessonHistoryDtoFactory INSTANCE;

    public static LessonHistoryDtoFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LessonHistoryDtoFactory();
        }
        return INSTANCE;
    }

    private LessonHistoryDtoFactory() {
    }

    private final LessonMapper lessonMapper = LessonMapper.getInstance();

    public LessonHistoryDto createLessonHistoryDto(List<Lesson> lessonList) {

        LessonHistoryDto lessonHistoryDto = new LessonHistoryDto();

        List<LessonDto> lessonDtoList =
                lessonList.stream()
                        .map(lessonMapper::toDto)
                        .toList();

        lessonHistoryDto.setLessons(lessonDtoList);

        return lessonHistoryDto;
    }
}

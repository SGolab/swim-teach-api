package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.LessonDto;
import com.golab.swimteach.dto.LessonHistoryDto;
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

    public LessonHistoryDto createLessonHistoryDto(List<Lesson> lessonList, String swimmerName) {

        LessonHistoryDto lessonHistoryDto = new LessonHistoryDto();

        List<LessonDto> lessonDtoList =
                lessonList.stream()
                        .map(lessonMapper::toDto)
                        .toList();

        lessonHistoryDto.setLessons(lessonDtoList);
        lessonHistoryDto.setSwimmerName(swimmerName);

        return lessonHistoryDto;
    }
}

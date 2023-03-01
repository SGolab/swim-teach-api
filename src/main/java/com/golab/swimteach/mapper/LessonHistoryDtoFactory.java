package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.LessonDto;
import com.golab.swimteach.dto.LessonHistoryDto;
import com.golab.swimteach.model.Lesson;

import java.util.Comparator;
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

        lessonList.sort((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()));

        List<LessonDto> lessonDtoList =
                lessonList.stream()
                        .map(lesson -> lessonMapper.toDto(lesson))
                        .toList();

        lessonHistoryDto.setLessons(lessonDtoList);
        lessonHistoryDto.setSwimmerName(swimmerName);

        return lessonHistoryDto;
    }
}

package com.golab.swimteach.dto;

import com.golab.swimteach.dto.LessonHistoryDto.LessonDto;
import com.golab.swimteach.dto.LessonHistoryDto.LessonDto.SkillMarkDto;
import com.golab.swimteach.model.Lesson;
import com.golab.swimteach.model.SkillMark;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class LessonHistoryDtoFactory {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static LessonHistoryDtoFactory INSTANCE;

    public static LessonHistoryDtoFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LessonHistoryDtoFactory();
        }
        return INSTANCE;
    }

    private LessonHistoryDtoFactory() {
    }

    public LessonHistoryDto createLessonHistoryDto(List<Lesson> lessonList) {

        LessonHistoryDto lessonHistoryDto = new LessonHistoryDto();

        List<LessonDto> lessonDtoList =
                lessonList.stream()
                        .map(this::toLessonDto)
                        .toList();

        lessonHistoryDto.setLessons(lessonDtoList);
        return lessonHistoryDto;
    }

    private LessonDto toLessonDto(Lesson lesson) {

        LessonDto lessonDto = new LessonDto();

        lessonDto.setDateTime(lesson.getDateTime().format(formatter));
        lessonDto.setLocation(lesson.getLocation());

        List<SkillMarkDto> skillMarkDtoList =
                lesson.getSkillMarks().stream()
                        .map(this::toSkillMarkDto)
                        .toList();

        lessonDto.setSkillMarks(skillMarkDtoList);

        return lessonDto;
    }

    private SkillMarkDto toSkillMarkDto(SkillMark skillMark) {

        SkillMarkDto skillMarkDto = new SkillMarkDto();

        skillMarkDto.setSkillDetailsId(skillMark.getSkillDetails().getId());
        skillMarkDto.setSkillDetailsTitle(skillMark.getSkillDetails().getTitle());
        skillMarkDto.setSkillStatus(skillMark.getSkillStatus().toString());

        return skillMarkDto;
    }
}

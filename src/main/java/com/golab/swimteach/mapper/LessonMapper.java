package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.LessonDto;
import com.golab.swimteach.dto.SkillMarkDto;
import com.golab.swimteach.model.Lesson;
import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.SkillMark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
public class LessonMapper {

    private static LessonMapper INSTANCE;

    public static LessonMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LessonMapper();
        }
        return INSTANCE;
    }

    private LessonMapper() {}

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    SkillMarkMapper skillMarkMapper = SkillMarkMapper.getInstance();

    public Lesson toLesson(LessonDto dto, List<SkillDetails> skillDetails) {
        Lesson lesson = new Lesson();

        lesson.setDateTime(LocalDateTime.parse(dto.getDate() + " " + dto.getTime(), dateTimeFormatter));
        lesson.setLocation(dto.getLocation());
        lesson.setSkillMarks(dto.getSkillMarks().stream().map(smdto -> skillMarkMapper.toSkillMark(smdto, skillDetails)).collect(Collectors.toSet()));

        return lesson;
    }

    public LessonDto toDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();

        lessonDto.setDate(lesson.getDateTime().format(dateFormatter));
        lessonDto.setTime(lesson.getDateTime().format(timeFormatter));
        lessonDto.setLocation(lesson.getLocation());
        lessonDto.setSkillMarks(lesson.getSkillMarks().stream().map(skillMark -> skillMarkMapper.toSkillMarkDto(skillMark)).toList());

        if (lesson.getHomework() != null) {
            lessonDto.setHomeworkId(lesson.getHomework().getId());
        }

        return lessonDto;
    }
}

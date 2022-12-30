package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.LessonDto;
import com.golab.swimteach.dto.LessonDto.SkillMarkDto;
import com.golab.swimteach.model.Lesson;
import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.SkillMark;
import com.golab.swimteach.model.SkillStatus;

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

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public Lesson toLesson(LessonDto dto, List<SkillDetails> skillDetails) {
        Lesson lesson = new Lesson();

        lesson.setDateTime(LocalDateTime.parse(dto.getDateTime(), formatter));
        lesson.setLocation(dto.getLocation());
        lesson.setSkillMarks(dto.getSkillMarks().stream().map(smdto -> toSkillMark(smdto, skillDetails)).collect(Collectors.toSet()));

        return lesson;
    }

    public LessonDto toDto(Lesson lesson) {
        LessonDto lessonDto = new LessonDto();

        lessonDto.setDateTime(lesson.getDateTime().format(formatter));
        lessonDto.setLocation(lesson.getLocation());
        lessonDto.setSkillMarks(lesson.getSkillMarks().stream().map(this::toSkillMarkDto).toList());

        return lessonDto;
    }

    private SkillMarkDto toSkillMarkDto(SkillMark skillMark) {
        SkillMarkDto skillMarkDto = new SkillMarkDto();

        skillMarkDto.setSkillDetailsId(skillMark.getSkillDetails().getId());
        skillMarkDto.setSkillDetailsTitle(skillMark.getSkillDetails().getTitle());
        skillMarkDto.setSkillStatus(skillMark.getSkillStatus().toString());

        return skillMarkDto;
    }

    private SkillMark toSkillMark(SkillMarkDto dto, List<SkillDetails> skillDetails) {
        SkillMark skillMark = new SkillMark();

        skillMark.setSkillDetails(
                skillDetails.stream()
                        .filter(sd -> sd.getId().equals(dto.getSkillDetailsId()))
                        .findFirst()
                        .orElseThrow()
        );
        skillMark.setSkillStatus(SkillStatus.valueOf(dto.getSkillStatus()));

        return skillMark;
    }
}

package com.golab.swimteach.services;

import com.golab.swimteach.dto.LessonDto;
import com.golab.swimteach.dto.LessonHistoryDto;
import com.golab.swimteach.dto.LessonHistoryDtoFactory;
import com.golab.swimteach.mapper.LessonMapper;
import com.golab.swimteach.model.Lesson;
import com.golab.swimteach.model.Skill;
import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.LessonRepository;
import com.golab.swimteach.repositories.SkillDetailsRepository;
import com.golab.swimteach.repositories.SwimmerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class LessonServiceImpl implements LessonService {

    private final SwimmerRepository swimmerRepository;

    private final LessonRepository lessonRepository;

    private final SkillDetailsRepository skillDetailsRepository;

    private final LessonHistoryDtoFactory lessonHistoryDtoFactory = LessonHistoryDtoFactory.getInstance();
    private final LessonMapper lessonMapper = LessonMapper.getInstance();

    @Override
    public LessonHistoryDto getLessonHistory() {
        return lessonHistoryDtoFactory.createLessonHistoryDto(List.of());
    }

    @Override
    public LessonHistoryDto getLessonHistory(Long swimmerId) {

        Swimmer swimmer = swimmerRepository.findById(swimmerId)
                .orElseThrow(() -> new RuntimeException("Swimmer Not Found")); //todo exceptions

        List<Lesson> lessonList = new ArrayList<>(swimmer.getLessonSet());

        lessonList.sort((l1, l2) -> {
            if (l1.getDateTime().isAfter(l2.getDateTime())) {
                return 1;
            } else if (l1.getDateTime().isBefore(l2.getDateTime())) {
                return -1;
            } else {
                return 0;
            }
        });

        LessonHistoryDto lessonHistoryDto =
                lessonHistoryDtoFactory.createLessonHistoryDto(lessonList);
        return lessonHistoryDto;
    }

    @Override
    public LessonDto createLesson(Long swimmerId, LessonDto lessonDto) {

        Swimmer swimmer = swimmerRepository.findById(swimmerId)
                .orElseThrow(() -> new RuntimeException("Swimmer Not Found")); //todo exceptions

        List<SkillDetails> skillDetails = lessonDto.getSkillMarks()
                .stream()
                .map(LessonDto.SkillMarkDto::getSkillDetailsId)
                .map(id -> skillDetailsRepository.findById(id).orElseThrow())
                .toList();

        Lesson lesson = lessonMapper.toLesson(lessonDto, skillDetails);
        Lesson savedLesson = lessonRepository.save(lesson);

        swimmer.addLesson(savedLesson);

        //changing status of the skill assigned to the swimmer
        savedLesson.getSkillMarks().forEach(skillMark -> {
            Skill foundSkill = swimmer.getSkillSet()
                    .stream()
                    .filter(skill -> Objects.equals(skill.getSkillDetails().getId(), skillMark.getSkillDetails().getId()))
                    .findFirst()
                    .orElseThrow();

            foundSkill.setStatus(skillMark.getSkillStatus());
        });

        swimmerRepository.save(swimmer);

        return lessonMapper.toDto(savedLesson);
    }
}

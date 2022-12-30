package com.golab.swimteach.services;

import com.golab.swimteach.dto.LessonDto;
import com.golab.swimteach.model.Skill;
import com.golab.swimteach.model.SkillStatus;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.LessonRepository;
import com.golab.swimteach.repositories.SkillDetailsRepository;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@DataJpaTest
@ComponentScan(basePackages = {
        "com.golab.swimteach.bootstrap",
        "com.golab.swimteach.repositories"
})
class LessonServiceIT {

    @Autowired
    SwimmerRepository swimmerRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    SkillDetailsRepository skillDetailsRepository;

    LessonService lessonService;

    @BeforeEach
    void setUp() {
        lessonService = new LessonServiceImpl(swimmerRepository, lessonRepository, skillDetailsRepository);
    }

    @Test
    void createLesson() {
        //prepare
        Swimmer swimmer = swimmerRepository.findAll().get(0);

        LessonDto lessonDto = LessonDto.builder()
                .dateTime("03.04.2000 12:00")
                .location("Test location")
                .skillMarks(List.of(
                        LessonDto.SkillMarkDto.builder().skillDetailsId(1L).skillStatus(SkillStatus.TRAINED.name()).build(),
                        LessonDto.SkillMarkDto.builder().skillDetailsId(2L).skillStatus(SkillStatus.ACQUIRED.name()).build()
                ))
                .build();

        lessonService.createLesson(swimmer.getId(), lessonDto);

        //test
        Swimmer swimmerUpdated = swimmerRepository.findById(swimmer.getId()).orElseThrow();

        Skill trainedSkill = swimmerUpdated.getSkillSet().stream().filter(skill -> skill.getSkillDetails().getId().equals(1L)).findFirst().orElseThrow();
        Skill acquiredSkill = swimmerUpdated.getSkillSet().stream().filter(skill -> skill.getSkillDetails().getId().equals(2L)).findFirst().orElseThrow();

        Assertions.assertEquals(trainedSkill.getStatus(), SkillStatus.TRAINED);
        Assertions.assertEquals(acquiredSkill.getStatus(), SkillStatus.ACQUIRED);

        Assertions.assertFalse(swimmerUpdated.getLessonSet().isEmpty());
    }
}
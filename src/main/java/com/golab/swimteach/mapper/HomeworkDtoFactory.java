package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.HomeworkListDto;
import com.golab.swimteach.model.Homework;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.golab.swimteach.dto.HomeworkListDto.*;

public class HomeworkDtoFactory {

    private static HomeworkDtoFactory INSTANCE;

    public static HomeworkDtoFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HomeworkDtoFactory();
        }
        return INSTANCE;
    }

    private HomeworkDtoFactory() {
    }

    public HomeworkListDto createHomeworkList(List<Homework> homeworkList) {

        HomeworkListDto homeworkDto = new HomeworkListDto();

        List<HomeworkItemDto> homeworkItemDtoList = homeworkList.stream().map(this::createHomeworkItemDto).toList();

        homeworkDto.setHomeworks(homeworkItemDtoList);

        return homeworkDto;
    }

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final SkillMapper skillMapper = SkillMapper.getInstance();

    private HomeworkItemDto createHomeworkItemDto(Homework homework) {

        HomeworkItemDto homeworkItemDto = new HomeworkItemDto();

        homeworkItemDto.setDate(dateFormatter.format(homework.getDateTime()));
        homeworkItemDto.setDescription(homework.getDescription());
        homeworkItemDto.setSkills(homework.getSkills().stream().map(skillMapper::toSkillDto).collect(Collectors.toList()));
        homeworkItemDto.setCustomSkills(homework.getCustomSkills());

        return homeworkItemDto;
    }

}

package com.golab.swimteach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeworkListDto {

    private List<HomeworkItemDto> homeworks;

    @Getter
    @Setter
    public static class HomeworkItemDto {
        private Long id;
        private String date;
        private String description;
        private List<TaskDto> tasks;
        private List<String> customSkills;
    }

}

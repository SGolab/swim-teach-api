package com.golab.swimteach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LessonHistoryDto {

    private List<LessonDto> lessons;

    @Getter
    @Setter
    static class LessonDto {
        private String dateTime;
        private String location;
        private List<SkillMarkDto> skillMarks;

        @Getter
        @Setter
        static class SkillMarkDto {
            private Long skillDetailsId;
            private String skillDetailsTitle;
            private String skillStatus;
        }
    }
}
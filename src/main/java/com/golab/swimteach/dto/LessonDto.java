package com.golab.swimteach.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
    private String dateTime;
    private String location;
    private List<SkillMarkDto> skillMarks;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SkillMarkDto {
        private Long skillDetailsId;
        private String skillDetailsTitle;
        private String skillStatus;
    }
}

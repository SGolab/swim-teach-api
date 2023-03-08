package com.golab.swimteach.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
    private String date;
    private String time;
    private String location;
    private List<SkillMarkDto> skillMarks;
    private Long homeworkId;
}

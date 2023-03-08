package com.golab.swimteach.dto;

import com.golab.swimteach.model.SkillStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillMarkDto {

    private Long detailsId;

    private SkillStatus status;
    private SkillStatus prevStatus;

    private String title;
    private String stageTitle;
    private String subjectTitle;

    private String description;
    private String url;
}

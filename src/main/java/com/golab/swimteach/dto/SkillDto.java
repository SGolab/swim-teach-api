package com.golab.swimteach.dto;

import com.golab.swimteach.model.SkillStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SkillDto {
    private Long detailsId;

    private SkillStatus status;

    private String title;
    private String stageTitle;
    private String subjectTitle;

    private String description;
    private String url;
}

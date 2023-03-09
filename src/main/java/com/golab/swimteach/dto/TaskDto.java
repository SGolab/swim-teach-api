package com.golab.swimteach.dto;

import com.golab.swimteach.model.UnitEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private String unit;
    private Integer amount;

    private Long detailsId;

    private String title;
    private String stageTitle;
    private String subjectTitle;
}

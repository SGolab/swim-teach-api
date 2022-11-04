package com.golab.swimteach.dto;

import com.golab.swimteach.model.GoalStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoalDto {
    private Long id;
    private GoalStatus status;
    private String title;
    private String description;
}

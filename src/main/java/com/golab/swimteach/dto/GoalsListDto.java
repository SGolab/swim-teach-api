package com.golab.swimteach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GoalsListDto {
    private List<GoalDto> goals;
}

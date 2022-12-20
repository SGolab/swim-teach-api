package com.golab.swimteach.dto;

import com.golab.swimteach.model.GoalDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GoalsTemplate {
    private List<GoalDetails> goals;
}

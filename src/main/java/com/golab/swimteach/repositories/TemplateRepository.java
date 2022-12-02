package com.golab.swimteach.repositories;

import com.golab.swimteach.dto.GoalsTemplate;
import com.golab.swimteach.dto.ProgressTreeTemplate;

public interface TemplateRepository {
    ProgressTreeTemplate getTreeTemplate();
    void setTreeTemplate(ProgressTreeTemplate progressTreeTemplate);

    GoalsTemplate getGoalTemplate();
    void setGoalTemplate(GoalsTemplate goalsTemplate);
}

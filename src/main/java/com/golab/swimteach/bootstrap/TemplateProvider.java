package com.golab.swimteach.bootstrap;

import com.golab.swimteach.dto.GoalsTemplate;
import com.golab.swimteach.dto.ProgressTreeTemplate;

public interface TemplateProvider {
    ProgressTreeTemplate getProgressTreeTemplate();

    GoalsTemplate getGoalsTemplate();
}

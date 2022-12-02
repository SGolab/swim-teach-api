package com.golab.swimteach.repositories;

import com.golab.swimteach.dto.GoalsTemplate;
import com.golab.swimteach.dto.ProgressTreeTemplate;
import org.springframework.stereotype.Component;

@Component
public class TemplateRepositoryImpl implements TemplateRepository {

    private ProgressTreeTemplate progressTreeTemplate;
    private GoalsTemplate goalsTemplate;

    @Override
    public ProgressTreeTemplate getTreeTemplate() {
        return progressTreeTemplate;
    }

    @Override
    public void setTreeTemplate(ProgressTreeTemplate progressTreeTemplate) {
        this.progressTreeTemplate = progressTreeTemplate;
    }

    @Override
    public GoalsTemplate getGoalTemplate() {
        return goalsTemplate;
    }

    @Override
    public void setGoalTemplate(GoalsTemplate goalsTemplate) {
        this.goalsTemplate = goalsTemplate;
    }
}

package com.golab.swimteach.repositories;

import com.golab.swimteach.dto.ProgressTreeTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProgressTreeTemplateRepositoryImpl implements ProgressTreeTemplateRepository {

    private ProgressTreeTemplate progressTreeTemplate;

    @Override
    public ProgressTreeTemplate getTreeTemplate() {
        return progressTreeTemplate;
    }

    @Override
    public void setTreeTemplate(ProgressTreeTemplate progressTreeTemplate) {
        this.progressTreeTemplate = progressTreeTemplate;
    }
}

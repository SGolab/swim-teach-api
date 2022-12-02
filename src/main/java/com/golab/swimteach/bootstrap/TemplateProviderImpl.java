package com.golab.swimteach.bootstrap;

import com.golab.swimteach.dto.GoalsTemplate;
import com.golab.swimteach.dto.ProgressTreeTemplate;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

@Component
public class TemplateProviderImpl implements TemplateProvider {

    private final String TREE_TEMPLATE_PATH;
    private final String GOALS_TEMPLATE_PATH;

    public TemplateProviderImpl(@Value("${progressTree.templatePath}") String treeTemplatePath,
                                @Value("${goals.templatePath}") String goalsTemplatePath) {
        TREE_TEMPLATE_PATH = treeTemplatePath;
        GOALS_TEMPLATE_PATH = goalsTemplatePath;
    }

    @Override
    public ProgressTreeTemplate getProgressTreeTemplate() {
        ClassPathResource resource = new ClassPathResource(TREE_TEMPLATE_PATH);

        try {
            String json = Files.readString(resource.getFile().toPath(), Charset.defaultCharset());

            ProgressTreeTemplate template = new Gson().fromJson(json, ProgressTreeTemplate.class);
            return template;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GoalsTemplate getGoalsTemplate() {
        ClassPathResource resource = new ClassPathResource(GOALS_TEMPLATE_PATH);

        try {
            String json = Files.readString(resource.getFile().toPath(), Charset.defaultCharset());

            GoalsTemplate template = new Gson().fromJson(json, GoalsTemplate.class);
            return template;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

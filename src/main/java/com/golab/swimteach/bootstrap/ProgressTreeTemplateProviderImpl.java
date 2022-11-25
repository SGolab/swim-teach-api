package com.golab.swimteach.bootstrap;

import com.golab.swimteach.dto.ProgressTreeTemplate;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

@Component
public class ProgressTreeTemplateProviderImpl implements ProgressTreeTemplateProvider {

    private final String TREE_TEMPLATE_PATH;

    public ProgressTreeTemplateProviderImpl(@Value("${progressTree.templatePath}") String treeTemplatePath) {
        TREE_TEMPLATE_PATH = treeTemplatePath;
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
}

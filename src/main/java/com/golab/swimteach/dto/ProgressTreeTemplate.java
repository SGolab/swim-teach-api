package com.golab.swimteach.dto;

import com.golab.swimteach.model.SkillDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ProgressTreeTemplate {

    private List<StageTemplate> stages;

    @Getter
    @Setter
    @ToString
    public static class StageTemplate {
        private String title;
        private List<SubjectTemplate> subjects;

        @Getter
        @Setter
        @ToString
        public static class SubjectTemplate {
            private String title;
            private List<SkillDetails> skills;
        }
    }

    public List<SkillDetails> getSkillDetailsList() {
        List<SkillDetails> result = new ArrayList<>();

        stages.stream()
                .flatMap(stage -> stage.getSubjects().stream())
                .flatMap(subject -> subject.getSkills().stream())
                .forEach(result::add);

        return result;
    }
}

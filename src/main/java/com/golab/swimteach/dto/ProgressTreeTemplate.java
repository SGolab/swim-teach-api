package com.golab.swimteach.dto;

import com.golab.swimteach.model.GoalDetails;
import com.golab.swimteach.model.SkillDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        private GoalDetails goal;

        @Getter
        @Setter
        @ToString
        public static class SubjectTemplate {
            private String title;
            private List<SkillDetails> skills;
            private GoalDetails goal;
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

    public List<GoalDetails> getGoalDetailsList() {
        List<GoalDetails> result = new ArrayList<>();

        stages.stream()
                .peek(stage -> result.add(stage.getGoal()))
                .flatMap(stage -> stage.getSubjects().stream())
                .map(subject -> subject.getGoal())
                .forEach(result::add);

        return result;
    }
}

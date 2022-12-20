package com.golab.swimteach.dto;

import com.golab.swimteach.model.GoalDetails;
import com.golab.swimteach.model.SkillDetails;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Stream<GoalDetails> stageGoalsStream = stages.stream()
                .map(StageTemplate::getGoal)
                .filter(Objects::nonNull);

        Stream<GoalDetails> subjectGoalsStream = stages.stream()
                .flatMap(stageTemplate -> stageTemplate.getSubjects().stream())
                .map(subjectTemplate -> subjectTemplate.getGoal())
                .filter(Objects::nonNull);

        return Stream.concat(stageGoalsStream, subjectGoalsStream).toList();
    }
}

package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.ProgressTreeDto;
import com.golab.swimteach.dto.ProgressTreeDto.Stage;
import com.golab.swimteach.dto.ProgressTreeDto.Stage.Subject;
import com.golab.swimteach.dto.ProgressTreeTemplate;
import com.golab.swimteach.dto.ProgressTreeTemplate.StageTemplate;
import com.golab.swimteach.dto.ProgressTreeTemplate.StageTemplate.SubjectTemplate;
import com.golab.swimteach.dto.SkillDto;
import com.golab.swimteach.mapper.GoalMapper;
import com.golab.swimteach.mapper.SkillMapper;
import com.golab.swimteach.model.Goal;
import com.golab.swimteach.model.Skill;
import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.SkillStatus;

import java.util.ArrayList;
import java.util.List;

public class ProgressTreeFactory {

    private static final SkillMapper skillMapper = SkillMapper.getInstance();
    private static final GoalMapper goalMapper = GoalMapper.getInstance();

    private ProgressTreeFactory() {
    }

    public static ProgressTreeDto createProgressTree(ProgressTreeTemplate template, List<Skill> skills, List<Goal> goals, String swimmerName) {
        ProgressTreeDto progressTreeDto = new ProgressTreeDto();
        progressTreeDto.setStages(createStages(template.getStages(), skills, goals));
        progressTreeDto.setSwimmerName(swimmerName);
        return progressTreeDto;
    }

    private static List<Stage> createStages(List<StageTemplate> stageTemplates,
                                            List<Skill> skills,
                                            List<Goal> goals) {
        List<Stage> sections = new ArrayList<>();

        stageTemplates.forEach(stageTemplate -> {
            Stage stage = new Stage();
            stage.setTitle(stageTemplate.getTitle());
            stage.setDescription(stageTemplate.getDescription());
            stage.setSubjects(createSubjects(stageTemplate.getSubjects(), skills, goals));
            stage.setGoal(goalMapper.toGoalDto(goals.stream()
                    .filter(g -> g.getDetails().getId().equals(stageTemplate.getGoal().getId()))
                    .findFirst()
                    .orElseThrow()));
            sections.add(stage);
        });

        return sections;
    }

    private static List<Subject> createSubjects(List<SubjectTemplate> subjectTemplates,
                                                List<Skill> skills,
                                                List<Goal> goals) {
        List<Subject> subjects = new ArrayList<>();

        subjectTemplates.forEach(subjectTemplate -> {
            Subject subject = new Subject();
            subject.setTitle(subjectTemplate.getTitle());
            subject.setSkills(insertSkills(subjectTemplate.getSkills(), skills));
            subject.setGoal(goalMapper.toGoalDto(goals.stream()
                    .filter(g -> g.getDetails().getId().equals(subjectTemplate.getGoal().getId()))
                    .findFirst()
                    .orElseThrow()));
            subjects.add(subject);
        });

        return subjects;
    }

    private static List<SkillDto> insertSkills(List<SkillDetails> skillDetailsList, List<Skill> skills) {
        List<Skill> result = new ArrayList<>();

        skillDetailsList.forEach(skillDetails -> {
            Skill skill = skills.stream()
                    .filter(s -> s.getSkillDetails().equals(skillDetails))
                    .findFirst()
                    .orElseThrow();

            result.add(skill);
        });

        return result.stream().map(skillMapper::toSkillDto).toList();
    }

    public static ProgressTreeDto createProgressTreeAllUnlocked(ProgressTreeTemplate template) {
        ProgressTreeDto progressTreeDto = new ProgressTreeDto();
        progressTreeDto.setStages(createStagesAllUnlocked(template.getStages()));
        progressTreeDto.setSwimmerName("Admin (all unlocked)");
        return progressTreeDto;
    }

    private static List<Stage> createStagesAllUnlocked(List<StageTemplate> stageTemplates) {
        List<Stage> stages = new ArrayList<>();

        stageTemplates.forEach(stageTemplate -> {
            Stage stage = new Stage();
            stage.setTitle(stageTemplate.getTitle());
            stage.setSubjects(createSubjectsAllUnlocked(stageTemplate.getSubjects()));
            stage.setGoal(goalMapper.toGoalDto(new Goal(stageTemplate.getGoal())));

            stages.add(stage);
        });

        return stages;
    }

    private static List<Subject> createSubjectsAllUnlocked(List<SubjectTemplate> subjectTemplates) {
        List<Subject> subjects = new ArrayList<>();

        subjectTemplates.forEach(subjectTemplate -> {
            Subject subject = new Subject();
            subject.setTitle(subjectTemplate.getTitle());
            subject.setSkills(insertSkillsAllUnlocked(subjectTemplate.getSkills()));
            subject.setGoal(goalMapper.toGoalDto(new Goal(subjectTemplate.getGoal())));
            subjects.add(subject);
        });

        return subjects;
    }

    private static List<SkillDto> insertSkillsAllUnlocked(List<SkillDetails> skillDetailsList) {
        return skillDetailsList.stream()
                .map(skillDetails -> new Skill(skillDetails, SkillStatus.ACQUIRED))
                .map(skillMapper::toSkillDto).toList();
    }
}

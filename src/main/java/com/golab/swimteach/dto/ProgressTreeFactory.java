package com.golab.swimteach.dto;

import com.golab.swimteach.dto.ProgressTreeDto.Stage;
import com.golab.swimteach.dto.ProgressTreeDto.Stage.Subject;
import com.golab.swimteach.dto.ProgressTreeTemplate.StageTemplate;
import com.golab.swimteach.dto.ProgressTreeTemplate.StageTemplate.SubjectTemplate;
import com.golab.swimteach.mapper.SkillMapper;
import com.golab.swimteach.model.Skill;
import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.SkillStatus;

import java.util.ArrayList;
import java.util.List;

public class ProgressTreeFactory {

    private static final SkillMapper skillMapper = SkillMapper.getInstance();

    private ProgressTreeFactory() {
    }

    public static ProgressTreeDto createProgressTree(ProgressTreeTemplate template, List<Skill> skills) {
        ProgressTreeDto progressTreeDto = new ProgressTreeDto();
        progressTreeDto.setStages(createStages(template.getStages(), skills));
        return progressTreeDto;
    }

    private static List<Stage> createStages(List<StageTemplate> stageTemplates,
                                            List<Skill> skills) {
        List<Stage> sections = new ArrayList<>();

        stageTemplates.forEach(stageTemplate -> {
            Stage section = new Stage();
            section.setTitle(stageTemplate.getTitle());
            section.setSubjects(createSubjects(stageTemplate.getSubjects(), skills));
            sections.add(section);
        });

        return sections;
    }

    private static List<Subject> createSubjects(List<SubjectTemplate> subjectTemplates, List<Skill> skills) {
        List<Subject> subjects = new ArrayList<>();

        subjectTemplates.forEach(subjectTemplate -> {
            Subject subject = new Subject();
            subject.setTitle(subjectTemplate.getTitle());
            subject.setSkills(insertSkills(subjectTemplate.getSkills(), skills));
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
        return progressTreeDto;
    }

    private static List<Stage> createStagesAllUnlocked(List<StageTemplate> stageTemplates) {
        List<Stage> sections = new ArrayList<>();

        stageTemplates.forEach(stageTemplate -> {
            Stage section = new Stage();
            section.setTitle(stageTemplate.getTitle());
            section.setSubjects(createSubjectsAllUnlocked(stageTemplate.getSubjects()));
            sections.add(section);
        });

        return sections;
    }

    private static List<Subject> createSubjectsAllUnlocked(List<SubjectTemplate> subjectTemplates) {
        List<Subject> subjects = new ArrayList<>();

        subjectTemplates.forEach(subjectTemplate -> {
            Subject subject = new Subject();
            subject.setTitle(subjectTemplate.getTitle());
            subject.setSkills(insertSkillsAllUnlocked(subjectTemplate.getSkills()));
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

package com.golab.swimteach.bootstrap;

import com.golab.swimteach.dto.GoalsTemplate;
import com.golab.swimteach.dto.ProgressTreeTemplate;
import com.golab.swimteach.model.*;
import com.golab.swimteach.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final SkillDetailsRepository skillDetailsRepository;

    private final GoalDetailsRepository goalDetailsRepository;

    private final TemplateProvider templateProvider;

    private final TemplateRepository templateRepository;

    private final SwimmerRepository swimmerRepository;

    private final UserRepository userRepository;

    public DataLoader(SkillDetailsRepository skillDetailsRepository, GoalDetailsRepository goalDetailsRepository, TemplateProvider progressTreeTemplateProvider, TemplateRepository progressTreeTemplateRepository, SwimmerRepository swimmerRepository, UserRepository userRepository) {
        this.skillDetailsRepository = skillDetailsRepository;
        this.goalDetailsRepository = goalDetailsRepository;
        this.templateProvider = progressTreeTemplateProvider;
        this.templateRepository = progressTreeTemplateRepository;
        this.swimmerRepository = swimmerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        loadTemplates();
        loadSwimmers();
        loadUsers();
    }

    private void loadTemplates() {
        ProgressTreeTemplate progressTreeTemplate = templateProvider.getProgressTreeTemplate();
        templateRepository.setTreeTemplate(progressTreeTemplate);

        GoalsTemplate goalsTemplate = templateProvider.getGoalsTemplate();
        templateRepository.setGoalTemplate(goalsTemplate);

        skillDetailsRepository.saveAll(progressTreeTemplate.getSkillDetailsList());
        goalDetailsRepository.saveAll(progressTreeTemplate.getGoalDetailsList());
    }

    private void loadUsers() {
        User admin = new User();
        admin.setSwimmer(null);
        admin.setUsername("admin");
        admin.setPassword("{noop}password");

        userRepository.save(admin);

        User client = new User();
        client.setSwimmer(swimmerRepository.findAll().get(0));
        client.setUsername("client");
        client.setPassword("{noop}password");

        userRepository.save(client);
    }

    private void loadSwimmers() {
        Set<SkillDetails> skillDetails = new HashSet<>(skillDetailsRepository.findAll());
        Set<GoalDetails> goalDetails = new HashSet<>(goalDetailsRepository.findAll());

        List<Swimmer> swimmerList = List.of(
                new Swimmer("AA", "BB", skillDetails, goalDetails,
                        Set.of(createLesson(skillDetails), createLesson(skillDetails), createLesson(skillDetails))),
                new Swimmer("CC", "DD", skillDetails, goalDetails,
                        Set.of(createLesson(skillDetails), createLesson(skillDetails), createLesson(skillDetails))));

        swimmerRepository.saveAll(swimmerList);
    }

    private Lesson createLesson(Set<SkillDetails> skillDetails) {
        Lesson lesson = new Lesson();
        lesson.setLocation("Conrada 6");
        lesson.setDateTime(LocalDateTime.now());

        SkillMark skillMark = new SkillMark();
        skillMark.setSkillStatus(SkillStatus.NOT_TRAINED);
        skillMark.setSkillDetails(
             skillDetails.stream().skip(new Random().nextInt(skillDetails.size())).findFirst().orElse(null)
        );

        lesson.setSkillMarks(Set.of(skillMark));

        return lesson;
    }
}

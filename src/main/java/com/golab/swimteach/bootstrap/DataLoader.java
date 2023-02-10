package com.golab.swimteach.bootstrap;

import com.golab.swimteach.dto.GoalsTemplate;
import com.golab.swimteach.dto.ProgressTreeTemplate;
import com.golab.swimteach.model.*;
import com.golab.swimteach.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    private final SkillDetailsRepository skillDetailsRepository;

    private final GoalDetailsRepository goalDetailsRepository;

    private final TemplateProvider templateProvider;

    private final TemplateRepository templateRepository;

    private final HomeworkRepository homeworkRepository;

    private final SwimmerRepository swimmerRepository;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public DataLoader(SkillDetailsRepository skillDetailsRepository, GoalDetailsRepository goalDetailsRepository, TemplateProvider progressTreeTemplateProvider, TemplateRepository progressTreeTemplateRepository, HomeworkRepository homeworkRepository, SwimmerRepository swimmerRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.skillDetailsRepository = skillDetailsRepository;
        this.goalDetailsRepository = goalDetailsRepository;
        this.templateProvider = progressTreeTemplateProvider;
        this.templateRepository = progressTreeTemplateRepository;
        this.homeworkRepository = homeworkRepository;
        this.swimmerRepository = swimmerRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);
        admin.setRoles(Set.of(adminRole));

        userRepository.save(admin);

        User client = new User();
        client.setSwimmer(swimmerRepository.findAll().get(0));
        client.setUsername("client");
        client.setPassword("{noop}password");
        Role clientRole = new Role();
        clientRole.setName("CLIENT");
        roleRepository.save(clientRole);
        client.setRoles(Set.of(clientRole));

        userRepository.save(client);
    }

    private void loadSwimmers() {
        Set<SkillDetails> skillDetails = new HashSet<>(skillDetailsRepository.findAll());
        Set<GoalDetails> goalDetails = new HashSet<>(goalDetailsRepository.findAll());

        List<Swimmer> swimmerList = List.of(
                new Swimmer("Bohdan", "Lutsak", skillDetails, goalDetails,
                        Set.of(createLesson(skillDetails),
                                createLesson(skillDetails),
                                createLesson(skillDetails),
                                createLesson(skillDetails),
                                createLesson(skillDetails),
                                createLesson(skillDetails),
                                createLesson(skillDetails),
                                createLesson(skillDetails),
                                createLesson(skillDetails),
                                createLesson(skillDetails),
                                createLesson(skillDetails)
                        )),
                new Swimmer("Grazynka", "Sadowy", skillDetails, goalDetails,
                        Set.of(createLesson(skillDetails), createLesson(skillDetails), createLesson(skillDetails))));

        //make skills in lessons match skills of swimmers
        swimmerList.forEach(swimmer -> {
            Set<Lesson> lessonSet = swimmer.getLessonSet();

            List<SkillMark> skillMarks = lessonSet.stream()
                    .flatMap(lesson -> lesson.getSkillMarks().stream())
                    .toList();

            skillMarks.forEach(skillMark -> {
                swimmer.getSkillSet().stream()
                        .filter(skill -> skill.getSkillDetails().getId() == skillMark.getSkillDetails().getId())
                        .findFirst()
                        .orElseThrow()
                        .setStatus(skillMark.getSkillStatus());
            });
        });

        List<Swimmer> savedSwimmers = swimmerRepository.saveAll(swimmerList);

        //create homeworks
        savedSwimmers.forEach(swimmer -> {
            for (int i = 0; i < 3; i++) {
                swimmer.addHomework(createHomework(swimmer.getSkillSet().stream().toList()));
            }
        });

        swimmerRepository.saveAll(savedSwimmers);
    }

    private Homework createHomework(List<Skill> skills) {
        Homework homework = new Homework();
        homework.setDescription("SAMPLE DESCRIPTION");
        homework.setDateTime(LocalDateTime.now());

        List<Skill> skillListFiltered = skills.stream().filter(skill -> skill.getStatus() != SkillStatus.NOT_TRAINED).toList();
        homework.setSkills(skillListFiltered.subList(0, new Random().nextInt(1, Math.min(skillListFiltered.size(), 5))));

        List<String> customSkills = List.of(
                "200m breaststroke",
                "300m butterfly",
                "4 x 25m underwater breaststroke",
                "125m kickboard legs only");

        homework.setCustomSkills(customSkills.subList(0, new Random().nextInt(1, customSkills.size())));

        return homework;
    }

    private Lesson createLesson(Set<SkillDetails> skillDetails) {
        Lesson lesson = new Lesson();
        lesson.setLocation("Conrada 6");
        lesson.setDateTime(LocalDateTime.now());

        lesson.setSkillMarks(createSkillMarkSetRandomSize(skillDetails));

        return lesson;
    }

    private Set<SkillMark> createSkillMarkSetRandomSize(Set<SkillDetails> skillDetails) {

        Set<SkillMark> result = new HashSet<>();

        for (int i = 0; i < Math.random() * 6; i++) {
            result.add(createSkillMark(skillDetails));
        }

        return result;
    }

    private SkillMark createSkillMark(Set<SkillDetails> skillDetails) {
        SkillMark skillMark = new SkillMark();
        skillMark.setSkillStatus(Math.random() > 0.5 ? SkillStatus.TRAINED : SkillStatus.ACQUIRED);
        skillMark.setSkillDetails(
                skillDetails.stream().skip(new Random().nextInt(skillDetails.size())).findFirst().orElse(null)
        );

        return skillMark;
    }
}

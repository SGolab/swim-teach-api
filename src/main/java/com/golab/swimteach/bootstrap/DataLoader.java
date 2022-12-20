package com.golab.swimteach.bootstrap;

import com.golab.swimteach.dto.GoalsTemplate;
import com.golab.swimteach.dto.ProgressTreeTemplate;
import com.golab.swimteach.model.GoalDetails;
import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.model.User;
import com.golab.swimteach.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                new Swimmer("AA", "BB", skillDetails, goalDetails),
                new Swimmer("CC", "DD", skillDetails, goalDetails)
        );

        swimmerRepository.saveAll(swimmerList);
    }
}

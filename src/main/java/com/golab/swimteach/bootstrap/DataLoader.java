package com.golab.swimteach.bootstrap;

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

@Component
public class DataLoader implements CommandLineRunner {

    private final SkillDetailsRepository skillDetailsRepository;

    private final GoalDetailsRepository goalDetailsRepository;

    private final ProgressTreeTemplateProvider progressTreeTemplateProvider;

    private final ProgressTreeTemplateRepository progressTreeTemplateRepository;

    private final SwimmerRepository swimmerRepository;

    private final UserRepository userRepository;

    public DataLoader(SkillDetailsRepository skillDetailsRepository, GoalDetailsRepository goalDetailsRepository, ProgressTreeTemplateProvider progressTreeTemplateProvider, ProgressTreeTemplateRepository progressTreeTemplateRepository, SwimmerRepository swimmerRepository, UserRepository userRepository) {
        this.skillDetailsRepository = skillDetailsRepository;
        this.goalDetailsRepository = goalDetailsRepository;
        this.progressTreeTemplateProvider = progressTreeTemplateProvider;
        this.progressTreeTemplateRepository = progressTreeTemplateRepository;
        this.swimmerRepository = swimmerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        loadProgressTreeTemplate();
        loadGoalDetails();
        loadSwimmers();
        loadUsers();
    }

    private void loadProgressTreeTemplate() {
        ProgressTreeTemplate template = progressTreeTemplateProvider.getProgressTreeTemplate();
        progressTreeTemplateRepository.setTreeTemplate(template);

        skillDetailsRepository.saveAll(template.getSkillDetailsList());
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

    private void loadGoalDetails() {
        List<GoalDetails> goalDetailsList = List.of(
                new GoalDetails("BASICS", "SAMPLE_DESC")
        );

        goalDetailsRepository.saveAll(goalDetailsList);
    }
}

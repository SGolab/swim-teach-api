package com.golab.swimteach.bootstrap;

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

    private final SwimmerRepository swimmerRepository;

    private final UserRepository userRepository;

    public DataLoader(SkillDetailsRepository skillDetailsRepository, GoalDetailsRepository goalDetailsRepository, SwimmerRepository swimmerRepository, UserRepository userRepository) {
        this.skillDetailsRepository = skillDetailsRepository;
        this.goalDetailsRepository = goalDetailsRepository;
        this.swimmerRepository = swimmerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        loadGoalDetails();
        loadSkillDetails();
        loadSwimmers();
        loadUsers();
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

    private static final String SAMPLE_DESC = "sample description";

    private void loadSkillDetails() {
        List<SkillDetails> skillDetailsList = List.of(
                new SkillDetails("Bubbles Mouth", SAMPLE_DESC),
                new SkillDetails("Bubbles Nose", SAMPLE_DESC),
                new SkillDetails("Dive Head Vertically", SAMPLE_DESC),
                new SkillDetails("Dive Head Horizontally", SAMPLE_DESC),
                new SkillDetails("Sit On Pool Bottom", SAMPLE_DESC));

        skillDetailsRepository.saveAll(skillDetailsList);
    }

    private void loadGoalDetails() {
        List<GoalDetails> goalDetailsList = List.of(
                new GoalDetails("BASICS", SAMPLE_DESC)
        );

        goalDetailsRepository.saveAll(goalDetailsList);
    }
}

package com.golab.swimteach.bootstrap;

import com.golab.swimteach.model.SkillDetails;
import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.SkillDetailsRepository;
import com.golab.swimteach.repositories.SkillRepository;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    SkillDetailsRepository skillDetailsRepository;
    SkillRepository skillRepository;
    SwimmerRepository swimmerRepository;

    public DataLoader(SkillDetailsRepository skillDetailsRepository, SkillRepository skillRepository, SwimmerRepository swimmerRepository) {
        this.skillDetailsRepository = skillDetailsRepository;
        this.skillRepository = skillRepository;
        this.swimmerRepository = swimmerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadSkillDetails();
        loadSwimmers();
    }

    private void loadSwimmers() {
        HashSet<SkillDetails> skillDetails = new HashSet<>(skillDetailsRepository.findAll());

        List<Swimmer> swimmerList = List.of(
                new Swimmer("AA", "BB", skillDetails),
                new Swimmer("CC", "DD", skillDetails)
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
}

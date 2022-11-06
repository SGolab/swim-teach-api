package com.golab.swimteach.services;

import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwimmerServiceImpl implements SwimmerService{

    private final SwimmerRepository swimmerRepository;

    public SwimmerServiceImpl(SwimmerRepository swimmerRepository) {
        this.swimmerRepository = swimmerRepository;
    }

    @Override
    public List<Swimmer> getAllSwimmers() {
        return swimmerRepository.findAll();
    }
}

package com.golab.swimteach.services;

import com.golab.swimteach.mapper.SwimmerMapper;
import com.golab.swimteach.dto.SwimmerDto;
import com.golab.swimteach.repositories.SwimmerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwimmerServiceImpl implements SwimmerService{

    private final SwimmerRepository swimmerRepository;

    private final SwimmerMapper swimmerMapper = SwimmerMapper.getInstance();

    public SwimmerServiceImpl(SwimmerRepository swimmerRepository) {
        this.swimmerRepository = swimmerRepository;
    }

    @Override
    public List<SwimmerDto> getAllSwimmers() {
        return swimmerRepository
                .findAll()
                .stream()
                .map(swimmerMapper::toDto)
                .toList();
    }
}

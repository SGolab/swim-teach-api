package com.golab.swimteach.services;

import com.golab.swimteach.dto.SwimmerDto;

import java.util.List;

public interface SwimmerService {

    List<SwimmerDto> getAllSwimmers();

    SwimmerDto getSwimmerInfo(Long swimmerId);

    SwimmerDto createNewSwimmer(SwimmerDto dto);
}

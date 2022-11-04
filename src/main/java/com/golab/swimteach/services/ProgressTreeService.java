package com.golab.swimteach.services;

import com.golab.swimteach.dto.ProgressTreeDto;

public interface ProgressTreeService {
    ProgressTreeDto getProgressTreeAllUnlocked(Long swimmerId);

    ProgressTreeDto getProgressTreeAllUnlocked();
}

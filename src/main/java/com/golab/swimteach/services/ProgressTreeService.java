package com.golab.swimteach.services;

import com.golab.swimteach.dto.ProgressTreeDto;

public interface ProgressTreeService {
    ProgressTreeDto getProgressTree(Long swimmerId);

    ProgressTreeDto getProgressTree();
}

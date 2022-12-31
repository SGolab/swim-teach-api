package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.SwimmerDto;
import com.golab.swimteach.services.SwimmerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SwimmerController {

    private final SwimmerService swimmerService;

    public SwimmerController(SwimmerService swimmerService) {
        this.swimmerService = swimmerService;
    }

    @GetMapping("/swimmers")
    public List<SwimmerDto> getAllSwimmers() {
        return swimmerService.getAllSwimmers();
    }
}

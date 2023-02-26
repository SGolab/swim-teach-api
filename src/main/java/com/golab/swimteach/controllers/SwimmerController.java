package com.golab.swimteach.controllers;

import com.golab.swimteach.dto.SwimmerDto;
import com.golab.swimteach.services.SwimmerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/swimmers/{swimmerId}/details")
    public SwimmerDto getSwimmerDetails(@PathVariable Long swimmerId) {
        return swimmerService.getSwimmerInfo(swimmerId);
    }

    @PostMapping("/swimmers/new")
    @ResponseStatus(HttpStatus.CREATED)
    public SwimmerDto getSwimmerDetails(@RequestBody SwimmerDto dto) {
        return swimmerService.createNewSwimmer(dto);
    }
}

package com.golab.swimteach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProgressTreeDto {

    private List<Stage> stages;

    @Getter
    @Setter
    static class Stage {

        private String title;
        private List<Subject> subjects;

        @Getter
        @Setter
        static
        class Subject {

            private String title;
            private List<SkillDto> skills;
        }
    }
}


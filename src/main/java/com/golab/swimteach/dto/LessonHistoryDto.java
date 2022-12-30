package com.golab.swimteach.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LessonHistoryDto {

    private List<LessonDto> lessons;
}
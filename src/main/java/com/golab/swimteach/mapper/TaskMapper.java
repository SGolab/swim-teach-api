package com.golab.swimteach.mapper;

import com.golab.swimteach.dto.TaskDto;
import com.golab.swimteach.model.Task;

public class TaskMapper {

    private static TaskMapper INSTANCE;

    public static TaskMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskMapper();
        }
        return INSTANCE;
    }

    public TaskDto toTaskDto(Task task) {
        TaskDto dto = new TaskDto();

        if (task.getSkillDetails() != null) {
            dto.setDetailsId(task.getSkillDetails().getId());
            dto.setTitle(task.getSkillDetails().getTitle());
            dto.setStageTitle(task.getSkillDetails().getStageTitle());
            dto.setSubjectTitle(task.getSkillDetails().getSubjectTitle());
        } else {
            dto.setTitle(task.getCustomTitle());
        }

        dto.setUnit(task.getUnit().toString());
        dto.setAmount(task.getAmount());

        return dto;
    }
}

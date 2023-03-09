package com.golab.swimteach.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime dateTime;

    @Lob
    private String description;

    @ManyToMany
    @JoinTable(
            name="homework_tasks",
            joinColumns = @JoinColumn( name="homework_id"),
            inverseJoinColumns = @JoinColumn( name="task_id")
    )
    private List<Task> tasks;
}

package com.golab.swimteach.repositories;

import com.golab.swimteach.model.Swimmer;
import com.golab.swimteach.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

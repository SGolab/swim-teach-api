package com.golab.swimteach.repositories;

import com.golab.swimteach.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}

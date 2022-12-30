package com.golab.swimteach.repositories;

import com.golab.swimteach.model.Lesson;
import com.golab.swimteach.model.Swimmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}

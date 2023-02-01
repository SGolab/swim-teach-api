package com.golab.swimteach.repositories;

import com.golab.swimteach.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}

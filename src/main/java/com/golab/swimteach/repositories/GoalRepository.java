package com.golab.swimteach.repositories;

import com.golab.swimteach.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}

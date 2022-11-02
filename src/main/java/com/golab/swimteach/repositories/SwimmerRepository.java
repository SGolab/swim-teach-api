package com.golab.swimteach.repositories;

import com.golab.swimteach.model.Swimmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwimmerRepository extends JpaRepository<Swimmer, Long> {
}

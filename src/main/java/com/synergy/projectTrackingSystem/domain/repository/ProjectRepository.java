package com.synergy.projectTrackingSystem.domain.repository;

import com.synergy.projectTrackingSystem.domain.entitiy.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

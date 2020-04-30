package com.synergy.projectTrackingSystem.service;

import com.synergy.projectTrackingSystem.domain.entitiy.Project;


public interface ProjectService {

    Project addProject(Project project);

    Project editProject(Project project);

    void deleteProject(long id);

    Iterable<Project> getAllProjects();

    Project getProjectById(long id);
}

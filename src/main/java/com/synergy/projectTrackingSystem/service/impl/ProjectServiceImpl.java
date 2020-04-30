package com.synergy.projectTrackingSystem.service.impl;


import com.synergy.projectTrackingSystem.domain.entitiy.Project;
import com.synergy.projectTrackingSystem.domain.repository.ProjectRepository;
import com.synergy.projectTrackingSystem.exception.RecordNotFoundException;
import com.synergy.projectTrackingSystem.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository repository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Project addProject(Project project) {
        return repository.save(project);
    }

    @Override
    public Project editProject(Project project) {
        long id = project.getId();
        Optional<Project> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            return repository.save(project);
        }
        throw new RecordNotFoundException("contact not found");
    }

    @Override
    public void deleteProject(long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Project> getAllProjects() {
        return repository.findAll();
    }

    @Override
    public Project getProjectById(long id) {
        return repository.getOne(id);
    }
}

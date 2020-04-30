package com.synergy.projectTrackingSystem.controller;

import com.synergy.projectTrackingSystem.domain.dto.ProjectCreateDto;
import com.synergy.projectTrackingSystem.domain.dto.ProjectDto;
import com.synergy.projectTrackingSystem.domain.entitiy.Project;
import com.synergy.projectTrackingSystem.mapper.ProjectMapper;
import com.synergy.projectTrackingSystem.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/projects")
@Validated
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @PostMapping
    public ResponseEntity<ProjectDto> addProject(@RequestBody @Valid ProjectCreateDto projectCreateDto) {

        return Stream.of(projectCreateDto)
                .map(projectMapper::toEntity)
                .map(projectService::addProject)
                .map(projectMapper::toDto)
                .map(ResponseEntity::ok)
                .findFirst().get();

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> editProject(@PathVariable @Positive long id, @Valid @RequestBody ProjectDto projectDto) {

        Project project = projectMapper.toEntity(id, projectDto);

        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        Project editedProject = projectService.editProject(project);
        return ResponseEntity.ok(projectMapper.toDto(editedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjectById(@PathVariable @Positive long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<ProjectDto> getAllProjects() {

        return StreamSupport.stream(projectService.getAllProjects().spliterator(), false)
                .map(projectMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable @Positive long id) {

        Project project = projectService.getProjectById(id);

        if (project == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(projectMapper.toDto(project));

    }
}

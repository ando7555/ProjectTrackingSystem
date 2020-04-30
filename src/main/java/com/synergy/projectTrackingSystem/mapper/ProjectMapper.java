package com.synergy.projectTrackingSystem.mapper;


import com.synergy.projectTrackingSystem.domain.dto.ProjectCreateDto;
import com.synergy.projectTrackingSystem.domain.dto.ProjectDto;
import com.synergy.projectTrackingSystem.domain.entitiy.Project;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class ProjectMapper {

    @Autowired
    private ModelMapper modelMapper;

    public static List<ProjectDto> toDtos(Iterable<Project> authors) {
        Type listType = new TypeToken<List<ProjectDto>>() {
        }.getType();
        return new ModelMapper().map(authors, listType);
    }

    public Project toEntity(ProjectDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Project.class);
    }

    public Project toEntity(long id, ProjectDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        } else {
            Project project = modelMapper.map(dto, Project.class);
            project.setId(id);
            return project;
        }
    }

    public Project toEntity(ProjectCreateDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Project.class);
    }

    public Project toCreateEntity(ProjectCreateDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Project.class);
    }

    public ProjectDto toDto(Project entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, ProjectDto.class);
    }
}
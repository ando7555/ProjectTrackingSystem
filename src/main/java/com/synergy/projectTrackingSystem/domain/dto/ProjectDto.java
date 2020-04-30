package com.synergy.projectTrackingSystem.domain.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.synergy.projectTrackingSystem.validator.EnumNamePattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
public class ProjectDto {

    @Getter
    private long id;

    @NotEmpty(message = "title is required")
    @Setter
    @Getter
    private String title;

    @NotNull(message = "status is required")
    @EnumNamePattern(regexp = "NEW|IN_PROGRESS|COMPLETED")
    @Setter
    @Getter
    private String status;

    @ElementCollection
    @JsonManagedReference
    @Setter
    @Getter
    private Set<@NotNull ContactDto> contacts;

    public ProjectDto(String title, String status, Set<ContactDto> contacts) {
        this.title = title;
        this.status = status;
        this.contacts = contacts;
    }
}

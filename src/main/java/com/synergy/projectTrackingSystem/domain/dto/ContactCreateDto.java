package com.synergy.projectTrackingSystem.domain.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
public class ContactCreateDto {

    @Pattern(regexp = "^[\\p{L} .'-]+$")
    @NotEmpty(message = "fullName is required")
    @Setter
    @Getter
    private String fullName;

    @Email
    @NotEmpty(message = "Email is required")
    @Setter
    @Getter
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})")
    @NotEmpty(message = "phone number is required")
    @Setter
    @Getter
    private String phoneNumber;

    @NotNull(message = "project is required")
    @JsonBackReference
    @Setter
    @Getter
    private ProjectDto project;

    public ContactCreateDto(String fullName, String email, String phoneNumber, ProjectDto project) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.project = project;
    }
}

package com.synergy.projectTrackingSystem.domain.entitiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Setter
    @Getter
    private long id;

    @Column(name = "full_name")
    @Setter
    @Getter
    private String fullName;

    @Column(name = "email")
    @Setter
    @Getter
    private String email;

    @Column(name = "phone_number")
    @Setter
    @Getter
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    @Setter
    @Getter
    private Project project;

    public Contact(String fullName, String email, String phoneNumber, Project project) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.project = project;
    }
}

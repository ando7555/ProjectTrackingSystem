package com.synergy.projectTrackingSystem.domain.entitiy;

import com.synergy.projectTrackingSystem.config.PostgreSQLEnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Set;

@Entity
@TypeDef(
        name = "status",
        typeClass = PostgreSQLEnumType.class
)
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Setter
    @Getter
    private long id;

    @Column(name = "title")
    @Setter
    @Getter
    private String title;

    @Column(name = "project_status")
    @Enumerated(EnumType.STRING)
    @Type(type = "status")
    @Setter
    @Getter
    private ProjectStatus status;

    @OneToMany(mappedBy = "project")
    @Setter
    @Getter
    private Set<Contact> contacts;

    public Project(String title, ProjectStatus status, Set<Contact> contacts) {
        this.title = title;
        this.status = status;
        this.contacts = contacts;
    }
}

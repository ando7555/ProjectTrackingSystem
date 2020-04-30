package com.synergy.projectTrackingSystem.domain.repository;

import com.synergy.projectTrackingSystem.domain.entitiy.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}

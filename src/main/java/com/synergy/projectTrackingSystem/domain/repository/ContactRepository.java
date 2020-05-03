package com.synergy.projectTrackingSystem.domain.repository;

import com.synergy.projectTrackingSystem.domain.entitiy.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Page<Contact> findByProjectId(Long postId, Pageable pageable);
    Optional<Contact> findByIdAndProjectId(Long id, Long projectId);
}

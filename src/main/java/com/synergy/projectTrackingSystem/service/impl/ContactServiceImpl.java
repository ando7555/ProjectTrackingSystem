package com.synergy.projectTrackingSystem.service.impl;

import com.synergy.projectTrackingSystem.domain.entitiy.Contact;
import com.synergy.projectTrackingSystem.domain.entitiy.Project;
import com.synergy.projectTrackingSystem.domain.repository.ContactRepository;
import com.synergy.projectTrackingSystem.domain.repository.ProjectRepository;
import com.synergy.projectTrackingSystem.exception.RecordNotFoundException;
import com.synergy.projectTrackingSystem.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {


    private final ContactRepository contactRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, ProjectRepository projectRepository) {
        this.contactRepository = contactRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Contact addContact(long projectId, Contact contact) {
      long id = contact.getProject().getId();
      Optional<Project> optionalProject = projectRepository.findById(id);
      if(optionalProject.isPresent()){
          contact.setProject(optionalProject.get());
          return contactRepository.save(contact);
      }
      throw new RecordNotFoundException("projectId " + projectId + " not found");
    }

    @Override
    public Contact editContact(Long projectId, Long contactId, Contact newContact) {
        if (!projectRepository.existsById(projectId)) {
            throw new RecordNotFoundException("ProjectId " + projectId + " not found");
        }

        return contactRepository.findById(contactId).map(contact -> {
            contact.setFullName(newContact.getFullName());
            contact.setEmail(newContact.getEmail());
            contact.setPhoneNumber(newContact.getPhoneNumber());
            return contactRepository.save(contact);
        }).orElseThrow(() -> new RecordNotFoundException("ContactId " + contactId + "not found"));
    }

    @Override
    public void deleteContact(long projectId, long contactId) {
        Optional<Contact> byIdAndProjectId = contactRepository.findByIdAndProjectId(contactId, projectId);
        byIdAndProjectId.ifPresent(contactRepository::delete);
        throw new RecordNotFoundException("Contact not found with id " + contactId + " and projectId " + projectId);
    }
}

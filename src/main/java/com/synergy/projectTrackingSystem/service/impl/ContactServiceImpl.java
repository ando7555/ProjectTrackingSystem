package com.synergy.projectTrackingSystem.service.impl;

import com.synergy.projectTrackingSystem.domain.entitiy.Contact;
import com.synergy.projectTrackingSystem.domain.repository.ContactRepository;
import com.synergy.projectTrackingSystem.exception.RecordNotFoundException;
import com.synergy.projectTrackingSystem.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {


    private ContactRepository repository;

    @Autowired
    public ContactServiceImpl(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contact addContact(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Contact editContact(Contact contact) {
        long id = contact.getId();
        Optional<Contact> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            return repository.save(contact);
        }
        throw new RecordNotFoundException("contact not found");
    }

    @Override
    public void deleteContact(long id) {
        repository.deleteById(id);
    }
}

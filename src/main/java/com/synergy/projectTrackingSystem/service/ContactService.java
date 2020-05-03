package com.synergy.projectTrackingSystem.service;

import com.synergy.projectTrackingSystem.domain.entitiy.Contact;

public interface ContactService {

    Contact addContact(long projectId, Contact contact);

    Contact editContact(Long projectId, Long contactId, Contact newContact);

    void deleteContact(long projectId, long contactId);
}

package com.synergy.projectTrackingSystem.service;

import com.synergy.projectTrackingSystem.domain.entitiy.Contact;

public interface ContactService {

    Contact addContact(Contact contact);

    Contact editContact(Contact contact);

    void deleteContact(long id);
}

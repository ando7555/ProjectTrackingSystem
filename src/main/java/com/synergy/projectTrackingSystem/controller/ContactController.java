package com.synergy.projectTrackingSystem.controller;

import com.synergy.projectTrackingSystem.domain.dto.ContactCreateDto;
import com.synergy.projectTrackingSystem.domain.dto.ContactDto;
import com.synergy.projectTrackingSystem.domain.entitiy.Contact;
import com.synergy.projectTrackingSystem.mapper.ContactMapper;
import com.synergy.projectTrackingSystem.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/contacts")
@Validated
public class ContactController {


    private final ContactService contactService;
    private final ContactMapper contactMapper;

    public ContactController(ContactService projectService, ContactMapper contactMapper) {
        this.contactService = projectService;
        this.contactMapper = contactMapper;
    }

    @PostMapping
    public ResponseEntity<ContactDto> addContact(@RequestBody @Valid ContactCreateDto contactCreateDto) {
        return Stream.of(contactCreateDto)
                .map(contactMapper::toEntity)
                .map(contactService::addContact)
                .map(contactMapper::toDto)
                .map(ResponseEntity::ok)
                .findFirst().get();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> editContact(@PathVariable @Positive long id, @Valid @RequestBody ContactDto contactDto) {

        Contact contact = contactMapper.toEntity(id, contactDto);

        if (contact == null) {
            return ResponseEntity.notFound().build();
        }

        Contact editedContact = contactService.editContact(contact);
        return ResponseEntity.ok(contactMapper.toDto(editedContact));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactById(@PathVariable long id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}

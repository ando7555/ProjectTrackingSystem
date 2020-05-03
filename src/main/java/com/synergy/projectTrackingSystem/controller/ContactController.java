package com.synergy.projectTrackingSystem.controller;

import com.synergy.projectTrackingSystem.domain.dto.ContactCreateDto;
import com.synergy.projectTrackingSystem.domain.dto.ContactDto;
import com.synergy.projectTrackingSystem.domain.entitiy.Contact;
import com.synergy.projectTrackingSystem.mapper.ContactMapper;
import com.synergy.projectTrackingSystem.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/contacts")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {


    private final ContactService contactService;
    private final ContactMapper contactMapper;

    public ContactController(ContactService contactService, ContactMapper contactMapper) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }

    @PostMapping("projects/{projectId}/contacts")
    public ResponseEntity<ContactDto> addContact(@PathVariable(value = "projectId") Long projectId,
                                                    @Valid @RequestBody ContactCreateDto contactDto) {
        Contact contact = contactService.addContact(projectId, contactMapper.toEntity(contactDto));
        return ok(contactMapper.toDto(contact));
    }

    @PutMapping("/projects/{projectId}/contacts/{contactId}")
    public ResponseEntity<ContactDto> editContact(@Positive @PathVariable (value = "projectId") Long projectId,
                                                  @Positive @PathVariable (value = "contactId") Long contactId,
                                                  @Valid @RequestBody ContactDto contactDto) {
        Contact contact = contactMapper.toEntity(contactDto);
        return ok(contactMapper.toDto(contactService.editContact(projectId,contactId,contact)));
    }

    @DeleteMapping("/projects/{projectId}/contacts/{contactId}")
    public ResponseEntity<Void> deleteContactById(@Positive @PathVariable (value = "projectId") Long projectId,
                                                  @Positive @PathVariable (value = "contactId") Long contactId) {
        contactService.deleteContact(projectId,contactId);
        return ResponseEntity.noContent().build();
    }
}

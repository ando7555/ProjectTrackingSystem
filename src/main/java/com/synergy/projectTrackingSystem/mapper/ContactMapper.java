package com.synergy.projectTrackingSystem.mapper;

import com.synergy.projectTrackingSystem.domain.dto.ContactCreateDto;
import com.synergy.projectTrackingSystem.domain.dto.ContactDto;
import com.synergy.projectTrackingSystem.domain.entitiy.Contact;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Component
public class ContactMapper {

    private final ModelMapper modelMapper;

    public ContactMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public static List<ContactDto> toDtos(Iterable<Contact> authors) {
        Type listType = new TypeToken<List<ContactDto>>() {
        }.getType();
        return new ModelMapper().map(authors, listType);
    }

    public Contact toEntity(ContactDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Contact.class);
    }

    public Contact toEntity(long id, ContactDto dto) {
        if (Objects.isNull(dto)) {
            return null;
        } else {
            Contact contact = modelMapper.map(dto, Contact.class);
            contact.setId(id);
            return contact;
        }

    }

    public Contact toEntity(ContactCreateDto dto) {
        return Objects.isNull(dto) ? null : modelMapper.map(dto, Contact.class);
    }

    public ContactDto toDto(Contact entity) {
        return Objects.isNull(entity) ? null : modelMapper.map(entity, ContactDto.class);
    }

}

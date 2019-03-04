package com.qs.contact.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.qs.contact.dao.Contact;
import com.qs.contact.dao.impl.ContactRepository;
import com.qs.contact.dto.ContactRequest;
import com.qs.contact.dto.ContactResponse;
import com.qs.contact.exception.ValidationException;
import com.qs.contact.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository dao;

	@Autowired
	private Mapper mapper;

	@Override
	public ContactResponse save(ContactRequest contactRequest) {
		Contact contact = buildContacEntity(contactRequest);
		return buildResponse(dao.save(contact));
	}

	@Override
	public ContactResponse update(Long id, ContactRequest contactRequest) throws ValidationException {

		Optional<Contact> contactEntity = dao.findById(id);

		if (!contactEntity.isPresent()) {
			throw new ValidationException("4030", "Persona con el id: " + id + " no existe en la bd",HttpStatus.OK);
		}

		Contact contact = contactEntity.get();

		contact.setName(contactRequest.getName());
		contact.setPhoneNumber(contactRequest.getPhoneNumber());
		contact.setDireccion(contactRequest.getDireccion());

		return buildResponse(dao.save(contact));
	}

	@Override
	public ContactResponse findById(Long id) throws ValidationException {
		Optional<Contact> contact = dao.findById(id);

		if (!contact.isPresent()) {
			
			throw new ValidationException("4160", "Contact con el id: " + id + " no existe en la bd",HttpStatus.OK);
		}

		return buildResponse(contact.get());
	}

	@Override
	public List<ContactResponse> findAllContact() throws ValidationException {

		Iterable<Contact> contact = dao.findAll();

		if (IterableUtils.size(contact) < 1) {
			throw new ValidationException("4030", "La lista de contactos esta vacia", HttpStatus.OK);
		}
		return buildResponseIterable(contact);
	}

	// UTIL
	private ContactResponse buildResponse(Contact contact) {
		return mapper.map(contact, ContactResponse.class);
	}

	private Contact buildContacEntity(ContactRequest contactRequest) {
		return mapper.map(contactRequest, Contact.class);
	}

	private List<ContactResponse> buildResponseIterable(Iterable<Contact> contactIterable) {
		List<Contact> ContactRS = (List<Contact>) contactIterable;

		List<ContactResponse> asDto = ContactRS.stream().map(new Function<Contact, ContactResponse>() {
		    @Override
		    public ContactResponse apply(Contact s) {

		        return new ContactResponse(s.getId(), s.getName() , s.getPhoneNumber());
		    }
		}).collect(Collectors.toList());
		return asDto;
		
		
	}
}

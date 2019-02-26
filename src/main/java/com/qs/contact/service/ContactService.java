package com.qs.contact.service;


import com.qs.contact.dao.Contact;
import com.qs.contact.dto.ContactRequest;
import com.qs.contact.dto.ContactResponse;
import com.qs.contact.exception.ValidationException;

public interface ContactService {
	ContactResponse update(Long id, ContactRequest contactRequest) throws ValidationException;
	ContactResponse save(ContactRequest contactRequest);
	ContactResponse findById(Long id) throws ValidationException;
	ContactResponse findAllContact() throws ValidationException;
	

}

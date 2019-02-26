package com.qs.contact.controller;

import java.util.Objects;
import java.util.stream.Stream;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qs.contact.dao.Contact;
import com.qs.contact.dto.ContactRequest;
import com.qs.contact.dto.ContactResponse;
import com.qs.contact.exception.ValidationException;
import com.qs.contact.service.ContactService;

@RestController
@RequestMapping("/people-service")
public class ContactController {

	@Autowired
	private ContactService contactService;

	// .../people-service/person
	// GuardarRegistro
	@PostMapping("/person")
	public HttpEntity<ContactResponse> save(@RequestBody ContactRequest contactRequest) throws ValidationException {
		validateCreateRequest(contactRequest);
		return new ResponseEntity<ContactResponse>(contactService.save(contactRequest), HttpStatus.CREATED);
	}

	// traeme todos
	@GetMapping("/person")
	public HttpEntity<ContactResponse> getAllPerson() throws ValidationException {

		System.out.println("Entrando a getAll");
		return new ResponseEntity<ContactResponse>(contactService.findAllContact(), HttpStatus.OK);

	}

	// BUsqueda por id
	@GetMapping("/person/{id}")
	public HttpEntity<ContactResponse> getPersonById(@PathVariable("id") Long id) throws ValidationException {

		return new ResponseEntity<ContactResponse>(contactService.findById(id), HttpStatus.OK);

	}

	// Editar Registro
	@PatchMapping("/person/{id}")
	public HttpEntity<ContactResponse> updatePersonById(@PathVariable("id") Long id,
			@RequestBody ContactRequest contactRequest) throws ValidationException {

		return new ResponseEntity<ContactResponse>(contactService.update(id, contactRequest), HttpStatus.OK);
	}

	private void validateCreateRequest(ContactRequest request) throws ValidationException {

		if (Stream.of(request.getName(), request.getPhoneNumber()).anyMatch(Objects::isNull)) {
			throw new ValidationException("4050", "El request no debe tener datos nulos", HttpStatus.BAD_REQUEST);
		}

	}

	// GET : recuperar datos
	// POST : enviar datos al server (añadir datos)
	// PUT : sirve para actualizar pero eliminando el registro actual e insertando
	// uno nuevo
	// PATCH: sirve para actualizar tambien pero propiedades en un registro
	// existente

}

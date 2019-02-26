package com.qs.contact.dao.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qs.contact.dao.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

}

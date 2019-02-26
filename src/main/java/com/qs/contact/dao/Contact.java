package com.qs.contact.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
public class Contact {
	
	@Id
	@GeneratedValue
	Long id;
	@NotNull(message="Este campo no puede estar en blanco")
	String name;
	String phoneNumber;
	@Email
	String direccion;
	

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Long id, String name, String phoneNumber, String direccion) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.direccion = direccion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", direccion=" + direccion
				+ "]";
	}

}

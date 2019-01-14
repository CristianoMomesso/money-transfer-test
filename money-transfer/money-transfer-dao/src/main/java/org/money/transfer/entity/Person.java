package org.money.transfer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Person implements BaseEntity<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2213172663243062012L;

	public Person() {
		super();
	}

	public Person(String firstName, String lastName, String document) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.document = document;
	}

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String document;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

}
package org.money.transfer.dao;

import javax.ejb.Stateless;

import org.money.transfer.entity.Person;

@Stateless
public class TesteBean extends BaseDAO<Person, Integer> {

	public void teste() {
		Person t = new Person();
		t.setFirstName("Cristiano");
		this.insert(t);
		System.out.println(t.toString());
	}
}

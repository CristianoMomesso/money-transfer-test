package org.money.transfer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Account implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5817830770740749859L;

	@Id
	private Long accountNumber;

	private Double balance;

	@NotNull
	@ManyToOne
	@Cascade(CascadeType.PERSIST)
	private Person accountOwner;

	public Account() {
		super();
	}

	public Account(Person accountOwner) {
		super();
		this.accountOwner = accountOwner;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Person getAccountOwner() {
		return accountOwner;
	}

	public void setAccountOwner(Person accountOwner) {
		this.accountOwner = accountOwner;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}

package org.money.transfer.dto;

import java.io.Serializable;

public class AccountDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9076803187772987920L;

	public AccountDTO() {
		super();
	}

	public AccountDTO(Long accountNumber, String firstName, String lastName, String ownerDocument, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.ownerDocument = ownerDocument;
		this.balance = balance;
	}

	private Long accountNumber;
	private String firstName;
	private String lastName;
	private String ownerDocument;
	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getOwnerDocument() {
		return ownerDocument;
	}

	public void setOwnerDocument(String ownerDocument) {
		this.ownerDocument = ownerDocument;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
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

}

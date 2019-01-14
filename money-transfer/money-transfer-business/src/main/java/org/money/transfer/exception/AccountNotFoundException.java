package org.money.transfer.exception;

public class AccountNotFoundException extends Exception {
	static final long serialVersionUID = -3387516993334229948L;

	public AccountNotFoundException(String message) {
		super(message);
	}

}
package org.money.transfer.exception;

public class InsufficientMoneyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3120152202866913822L;

	public InsufficientMoneyException(String message) {
		super(message);
	}

}
package org.money.transfer.dto;

import java.io.Serializable;

public class AccountTransferDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6059686339637278480L;
	
	private Long accountNumberOrigin;
	private Long accountNumberDestination;
	private double valueTransfer;

	public double getValueTransfer() {
		return valueTransfer;
	}

	public void setValueTransfer(double valueTransfer) {
		this.valueTransfer = valueTransfer;
	}

	public Long getAccountNumberOrigin() {
		return accountNumberOrigin;
	}

	public void setAccountNumberOrigin(Long accountNumberOrigin) {
		this.accountNumberOrigin = accountNumberOrigin;
	}

	public Long getAccountNumberDestination() {
		return accountNumberDestination;
	}

	public void setAccountNumberDestination(Long accountNumberDestination) {
		this.accountNumberDestination = accountNumberDestination;
	}

}

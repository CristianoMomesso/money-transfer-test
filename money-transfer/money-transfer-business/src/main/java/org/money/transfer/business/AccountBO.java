package org.money.transfer.business;

import org.money.transfer.dto.AccountDTO;
import org.money.transfer.dto.AccountTransferDTO;
import org.money.transfer.exception.AccountNotFoundException;
import org.money.transfer.exception.InsufficientMoneyException;
import org.money.transfer.exception.InvalidValuesException;

public interface AccountBO {

	AccountDTO creatAccount(AccountDTO accDto);

	AccountDTO findAccountByAcNumber(Long acountNumber) throws AccountNotFoundException;

	void transfer(AccountTransferDTO accTransfer)
			throws AccountNotFoundException, InsufficientMoneyException, InvalidValuesException;
}

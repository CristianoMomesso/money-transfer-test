package org.money.transfer.business.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.money.transfer.business.AccountBO;
import org.money.transfer.dao.AccountDAO;
import org.money.transfer.dto.AccountDTO;
import org.money.transfer.dto.AccountTransferDTO;
import org.money.transfer.entity.Account;
import org.money.transfer.entity.Person;
import org.money.transfer.exception.AccountNotFoundException;
import org.money.transfer.exception.InsufficientMoneyException;
import org.money.transfer.exception.InvalidValuesException;

@Stateless
public class AccountBOImpl implements AccountBO {

	@Inject
	private AccountDAO dao;

	@Override
	public AccountDTO creatAccount(AccountDTO accDto) {
		Account account = new Account();
		Person person = new Person(accDto.getFirstName(), accDto.getLastName(), accDto.getOwnerDocument());
		account.setAccountOwner(person);
		account.setBalance(accDto.getBalance());
		if (accDto.getAccountNumber() != 0) {
			account.setAccountNumber(accDto.getAccountNumber());
		}
		dao.insert(account);
		accDto.setAccountNumber(account.getAccountNumber());
		return accDto;
	}

	@Override
	public AccountDTO findAccountByAcNumber(Long acountNumber) throws AccountNotFoundException {
		Account acc = dao.find(acountNumber);
		if (acc == null) {
			throw new AccountNotFoundException("Account does not exist!");
		} else {
			AccountDTO dto = new AccountDTO(acc.getAccountNumber(), acc.getAccountOwner().getFirstName(),
					acc.getAccountOwner().getLastName(), acc.getAccountOwner().getDocument(), acc.getBalance());
			return dto;
		}
	}

	@Override
	public void transfer(AccountTransferDTO accTransfer)
			throws AccountNotFoundException, InsufficientMoneyException, InvalidValuesException {
		if (accTransfer == null) {
			throw new InvalidValuesException("Invalid Parameter!");
		}

		Account a1 = dao.find(accTransfer.getAccountNumberOrigin());
		Account a2 = dao.find(accTransfer.getAccountNumberDestination());

		if (a1 == null || a2 == null) {
			throw new AccountNotFoundException("Account does not exist!");
		}

		if (accTransfer.getValueTransfer() > a1.getBalance()) {
			throw new InsufficientMoneyException("Account origin does not have enough money!");
		}
		a1.setBalance(a1.getBalance() - accTransfer.getValueTransfer());
		a2.setBalance(a2.getBalance() + accTransfer.getValueTransfer());
	}

}

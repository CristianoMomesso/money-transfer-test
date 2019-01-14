package org.money.transfer.business.test;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.money.transfer.business.impl.AccountBOImpl;
import org.money.transfer.dao.AccountDAO;
import org.money.transfer.dto.AccountTransferDTO;
import org.money.transfer.entity.Account;
import org.money.transfer.exception.AccountNotFoundException;
import org.money.transfer.exception.InsufficientMoneyException;
import org.money.transfer.exception.InvalidValuesException;

public class AccountTest {

	@InjectMocks
	private transient AccountBOImpl ejb;

	@Mock
	private transient AccountDAO dao;

	public AccountTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAccountCorrect()
			throws AccountNotFoundException, InsufficientMoneyException, InvalidValuesException {
		AccountTransferDTO accTransfer = new AccountTransferDTO();
		accTransfer.setAccountNumberOrigin(1l);
		accTransfer.setAccountNumberDestination(2l);
		accTransfer.setValueTransfer(10d);

		Account a1 = new Account();
		a1.setAccountNumber(1l);
		a1.setBalance(100d);

		Account a2 = new Account();
		a2.setAccountNumber(2l);
		a2.setBalance(100d);

		Mockito.when(this.dao.find(1l)).thenReturn(a1);
		Mockito.when(this.dao.find(2l)).thenReturn(a2);
		ejb.transfer(accTransfer);
		Assert.assertTrue(a1.getBalance().equals(90d));
		Assert.assertTrue(a2.getBalance().equals(110d));
	}

	@Test(expected = AccountNotFoundException.class)
	public void testAccountNull() throws AccountNotFoundException, InsufficientMoneyException, InvalidValuesException {
		AccountTransferDTO accTransfer = new AccountTransferDTO();
		accTransfer.setAccountNumberOrigin(1l);
		accTransfer.setAccountNumberDestination(2l);
		accTransfer.setValueTransfer(10d);

		Account a1 = new Account();
		a1.setAccountNumber(1l);
		a1.setBalance(100d);

		Mockito.when(this.dao.find(1l)).thenReturn(a1);
		Mockito.when(this.dao.find(2l)).thenReturn(null);
		ejb.transfer(accTransfer);
	}

	@Test(expected = InvalidValuesException.class)
	public void testAccountInvalidValues()
			throws AccountNotFoundException, InsufficientMoneyException, InvalidValuesException {
		ejb.transfer(null);
	}
}

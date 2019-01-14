package org.money.transfer.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import org.money.transfer.entity.Account;

@Stateless
public class AccountDAO extends BaseDAO<Account, Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4121084044544513286L;

}

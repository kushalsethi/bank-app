package com.kushals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kushals.dao.AccountDao;
import com.kushals.model.Account;

/**
 * Handles all account operations
 * 
 * @author Kushal
 *
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;

	@Override
	public boolean createAccount(Account account) {
		return accountDao.createAccount(account);
	}

	@Override
	public boolean depositMoney(long money, long id) {
		return accountDao.depositMoney(money, id);
	}

	@Override
	public boolean withdrawMoney(long money, long id) {
		return accountDao.withdrawMoney(money, id);
	}

	@Override
	public Account enquireAccount(long id) {
		return accountDao.enquireAccount(id);
	}

}

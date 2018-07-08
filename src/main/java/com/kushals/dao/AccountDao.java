package com.kushals.dao;

import java.util.List;

import com.kushals.model.Account;

public interface AccountDao {

	public boolean createAccount(Account account);

	public List<Account> getAccount();

	public boolean depositMoney(long money, long id);

	public boolean withdrawMoney(long money, long id);

	public Account enquireAccount(long id);

}

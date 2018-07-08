package com.kushals.service;

import com.kushals.model.Account;

public interface AccountService {

	public boolean createAccount(Account account);

	public boolean depositMoney(long money, long id);

	public boolean withdrawMoney(long money, long id);

	public Account enquireAccount(long id);

}

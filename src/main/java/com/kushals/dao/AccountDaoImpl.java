package com.kushals.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kushals.model.Account;
import com.kushals.repository.AccountRepository;

@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private AccountRepository accountRepository;

	/**
	 * Creates account
	 */
	public boolean createAccount(Account account) {

		if (!accountExists(account)) {
			accountRepository.save(account);
			return true;
		}
		return false;
	}

	/**
	 * Checks if account already exists or not
	 * 
	 * @param account
	 * @return
	 */
	private boolean accountExists(Account account) {
		List<Account> accountList = getAccount();
		if (accountList.stream().anyMatch(acc -> acc.getEmail().equals(account.getEmail()))) {
			return true;
		}
		return false;
	}

	/**
	 * Return list of all accounts
	 */
	public List<Account> getAccount() {
		List<Account> accountList = (List<Account>) accountRepository.findAll();
		return accountList;
	}

	/**
	 * Deposits money to account
	 */
	public boolean depositMoney(long money, long id) {
		Account account = enquireAccount(id);
		long balance = account.getBalance() + money;
		account.setBalance(balance);
		return true;
	}

	/**
	 * Withdraws money from account
	 */
	public boolean withdrawMoney(long money, long id) {
		Account account = enquireAccount(id);
		if (account.getBalance() >= money) {
			long balance = account.getBalance() - money;
			account.setBalance(balance);
			return true;
		}
		return false;
	}

	/**
	 * Enquires requested account
	 */
	public Account enquireAccount(long id) {
		Optional<Account> account = accountRepository.findById(id);
		return account.get();
	}

}

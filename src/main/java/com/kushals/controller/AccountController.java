package com.kushals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kushals.model.Account;
import com.kushals.model.ResponseMessage;
import com.kushals.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Account> getAccountById(@PathVariable("id") long id) {
		System.out.println("Fetching Account with id " + id);
		Account account = accountService.enquireAccount(id);
		if (account == null) {
			return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> createAccount(@RequestBody Account account, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating account for " + account.getCustomerName());
		if (accountService.createAccount(account)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/account/{id}").buildAndExpand(account.getId()).toUri());
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Account created successfuly!!!"), headers, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage("Account with same email id already exists, please choose different email id!"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/deposit", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> depositToAccount(@RequestParam("id") long id, @RequestParam("money") long money) {
		System.out.println("Depositing money for " + id);
		if (accountService.enquireAccount(id) == null) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Account does not exist!"), HttpStatus.BAD_REQUEST);
		} else {
			if (accountService.depositMoney(money, id)) {
				return new ResponseEntity<ResponseMessage>(new ResponseMessage("Money deposited to account!"), HttpStatus.OK);
			} else {
				return new ResponseEntity<ResponseMessage>(new ResponseMessage("Transaction failed, please try again!"), HttpStatus.OK);
			}
		}
	}

	@PutMapping(value = "/withdraw", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseMessage> withdrawFromAccount(@RequestParam("id") long id, @RequestParam("money") long money) {
		System.out.println("Withdrawing money for " + id);
		if (accountService.enquireAccount(id) == null) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage("Account does not exist!"), HttpStatus.BAD_REQUEST);
		} else {
			if (accountService.withdrawMoney(money, id)) {
				return new ResponseEntity<ResponseMessage>(new ResponseMessage("Money withdrawn from account!"), HttpStatus.OK);
			} else {
				return new ResponseEntity<ResponseMessage>(new ResponseMessage("Insufficient amount, please try again!"), HttpStatus.OK);
			}
		}
	}
	
	

}

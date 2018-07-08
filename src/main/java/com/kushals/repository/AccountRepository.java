package com.kushals.repository;

import org.springframework.data.repository.CrudRepository;

import com.kushals.model.Account;

/**
 * Repository for CRUD operation on Account entity
 * @author Kushal
 *
 */
public interface AccountRepository extends CrudRepository<Account, Long> {

}

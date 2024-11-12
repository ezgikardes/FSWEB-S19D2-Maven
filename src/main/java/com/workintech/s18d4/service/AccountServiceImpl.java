package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.exception.AccountException;
import com.workintech.s18d4.repository.AccountRepository;
import com.workintech.s18d4.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    CustomerRepository customerRepository;
    AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account find(long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }


    @Override
    public Account delete(long id) {
        Account account = find(id);
        accountRepository.delete(account);
        return account;
    }
}

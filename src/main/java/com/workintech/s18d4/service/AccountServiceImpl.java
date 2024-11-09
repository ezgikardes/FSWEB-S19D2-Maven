package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{
    AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()){
            return null;
            //TODO exception
        }
        return accounts;
    }

    @Override
    public Account findById(Long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            return optionalAccount.get();
        }
        return null;
        //TODO exception
    }

    @Override
    public Account save(Account account) {
        if (account == null) {
            return null;
            //TODO exception
        }
        return accountRepository.save(account);
    }

    @Override
    public Account update(Long id, Account account) {
        if (account == null){
            return null;
            //TODO exception
        }
        accountRepository.save(account);
        return findById(id);
    }

    @Override
    public Account delete(Long id) {
        return findById(id);
    }
}

package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    private AccountResponse convertToAccountResponse(Account account){
        return new AccountResponse(
                account.getId(),
                account.getAccountName(),
                account.getMoneyAmount(),
                new CustomerResponse(
                        account.getCustomer().getId(),
                        account.getCustomer().getEmail(),
                        account.getCustomer().getSalary()
                )
        );
    }

    @GetMapping
    public List<AccountResponse> findAll(){
        List<Account> accounts = accountService.findAll();
        List<AccountResponse> accountResponses = new ArrayList<>();
        for(Account account : accounts){
        accountResponses.add(convertToAccountResponse(account));
        }
        return accountResponses;
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable Long id){
        Account account = accountService.findById(id);
        return convertToAccountResponse(account);
    }

    @PostMapping
    public AccountResponse save(@RequestBody Account account){
        accountService.save(account);
        return convertToAccountResponse(account);
    }

    @PutMapping("/{id}")
    public AccountResponse update(@PathVariable Long id, @RequestBody Account account){
        accountService.update(id, account);
        return convertToAccountResponse(account);
    }

    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable Long id){
        Account account = accountService.findById(id);
        accountService.delete(id);
        return convertToAccountResponse(account);
    }
}

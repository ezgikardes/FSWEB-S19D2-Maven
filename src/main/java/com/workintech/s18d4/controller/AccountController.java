package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.exception.AccountException;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    private CustomerService customerService;


    private AccountResponse convertToAccountResponse(Account account){
        if (account.getCustomer() == null) {
            throw new AccountException("Customer cannot be null", HttpStatus.BAD_REQUEST);
        }
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
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable long id){
        Account account = accountService.find(id);
        return convertToAccountResponse(account);
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@PathVariable long customerId, @RequestBody Account account){
        Customer customer = customerService.find(customerId);
        if (customer != null){
            customer.addAccount(account);
            account.setCustomer(customer);
            accountService.save(account);
        } else {
            throw new AccountException("Customer cannot be null", HttpStatus.BAD_REQUEST);
        }
        return convertToAccountResponse(account);
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@PathVariable long customerId, @RequestBody Account account){
        Customer customer = customerService.find(customerId); // find customer to update
        Account toBeUpdatedAccount = null; // declare a null Account object
        for (Account existingAccount : customer.getAccounts()){
            if (account.getId() == existingAccount.getId()){ //if an account ID matches to an ID in list,
                toBeUpdatedAccount = existingAccount; // assign this Account in the list to our new Account object (current Account).
            }
        }
        if (toBeUpdatedAccount == null){
            throw new AccountException("Account with ID" + account.getId() + "cannot be found for this customer", HttpStatus.NOT_FOUND);
        }

        int indexOfToBeUpdated = customer.getAccounts().indexOf(toBeUpdatedAccount); //now find the index of our current Account object
        customer.getAccounts().set(indexOfToBeUpdated, account); //set given Account object to this index
        account.setCustomer(customer); //set customer to given object
        accountService.save(account); //save given Account to account list.
        return convertToAccountResponse(account); // return converted account
    }



    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable long id){
        Account account = accountService.find(id);
        if (account == null){
            throw new AccountException("No account found", HttpStatus.NOT_FOUND);
        }
        accountService.delete(id);
        return convertToAccountResponse(account);
    }
}

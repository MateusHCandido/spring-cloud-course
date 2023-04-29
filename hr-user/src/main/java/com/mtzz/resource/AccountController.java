package com.mtzz.resource;


import com.mtzz.domain.Account;
import com.mtzz.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController
{
    @Autowired
    private AccountRepository accountRepository;


    @GetMapping(path = "/{accountId}")
    public ResponseEntity<Account> findById(@PathVariable Long accountId)
    {
        Account account = accountRepository.findById(accountId).get();
        return ResponseEntity.ok(account);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Account> findByEmail(@RequestParam String emailAccount)
    {
        try
        {
            Account account = accountRepository.findByEmailAccount(emailAccount);
            return ResponseEntity.ok().body(account);
        }
        catch (IllegalArgumentException exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}

package com.mtzz.resource;


import com.mtzz.domain.Account;
import com.mtzz.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController
{
    @Autowired
    private AccountService accountService;


    @GetMapping(path = "/search")
    public ResponseEntity<Account> findByEmail(@RequestParam String emailAccount)
    {
        try
        {
            Account account = accountService.findBy(emailAccount);
            return ResponseEntity.ok().body(account);
        }
        catch (IllegalArgumentException exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}

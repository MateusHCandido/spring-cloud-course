package com.mtzz.service;

import com.mtzz.domain.Account;
import com.mtzz.feignclients.AccountFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService
{
    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountFeignClient accountFeignClient;


    public Account findBy(String emailAccount)
    {
        Account account = accountFeignClient.findBy(emailAccount).getBody();
        if(account == null)
        {
            logger.error("Email not found: " + emailAccount);
        }
        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String emailAccount) throws UsernameNotFoundException {
        Account account = accountFeignClient.findBy(emailAccount).getBody();
        if(account == null)
        {
            logger.error("Email not found: " + emailAccount);
        }
        return account;
    }
}

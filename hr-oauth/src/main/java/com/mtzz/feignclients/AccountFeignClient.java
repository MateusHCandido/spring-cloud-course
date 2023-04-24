package com.mtzz.feignclients;

import com.mtzz.domain.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "hr-user", path = "/accounts")
public interface AccountFeignClient
{
    @GetMapping(path = "/search")
    ResponseEntity<Account> findBy(@RequestParam String emailAccount);
}

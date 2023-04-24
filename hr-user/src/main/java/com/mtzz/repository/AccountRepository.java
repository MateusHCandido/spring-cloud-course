package com.mtzz.repository;

import com.mtzz.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>
{
    Account findBy(String emailAccount);
}

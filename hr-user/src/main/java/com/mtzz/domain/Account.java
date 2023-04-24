package com.mtzz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_credentials")
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String nameAccount;
    @Column(unique = true)
    private String emailAccount;
    private String passwordAccount;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_access_permission"
    , joinColumns = @JoinColumn(name = "account_id")
    , inverseJoinColumns = @JoinColumn(name = "access_permission_id"))
    private Set<AccessPermission> permissions = new HashSet<>();
}

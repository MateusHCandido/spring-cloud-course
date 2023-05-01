package com.mtzz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails
{
    private Long accountId;
    private String nameAccount;
    private String emailAccount;
    private String passwordAccount;
    private Set<AccessPermission> permissions = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return permissions.stream().map(x -> new SimpleGrantedAuthority(x.getPermissionType()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername()
    {
        return emailAccount;
    }

    @Override
    public String getPassword()
    {
        return passwordAccount;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}

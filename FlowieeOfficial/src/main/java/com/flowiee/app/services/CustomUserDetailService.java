package com.flowiee.app.services;

import com.flowiee.app.model.admin.Account;
import com.flowiee.app.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Account> list = accountRepository.findByUsername(username);
        UserDetails userDetails = null;
        if (list.size() > 0) {
            System.out.println("Thành công");
            Account account = list.get(0);

            List<GrantedAuthority> grantlist = new ArrayList<GrantedAuthority>();
            GrantedAuthority authority = new SimpleGrantedAuthority("USER");
            grantlist.add(authority);

            userDetails = new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), grantlist);

            System.out.println("account.getUsername() " + account.getUsername());
            System.out.println("account.getPassword() " + account.getPassword());
        } else {
            System.out.println("Thất bại");
        }
        return userDetails;
    }
}

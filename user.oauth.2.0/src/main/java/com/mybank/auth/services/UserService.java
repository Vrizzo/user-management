package com.mybank.auth.services;

import com.mybank.auth.repositories.UserRepository;
import com.mybank.user.core.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return org.springframework.security.core.userdetails.User.
                withUsername(username).
                password(user.getAccount().getPassword()).
                authorities(user.getAccount().getRoles()) .
                accountExpired(false).
                accountLocked(false).
                credentialsExpired(false).build();
    }
}

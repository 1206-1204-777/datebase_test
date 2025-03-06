package com.example.date.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.date.entity.UserDataEntity;
import com.example.date.repository.TestDataRepository;

/*自動ログイン用サービス*/
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TestDataRepository repository;

    public CustomUserDetailsService(TestDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDataEntity user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of()  // 権限リスト、今回は空
        );
    }
}

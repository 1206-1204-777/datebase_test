package com.example.date.service;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.date.entity.UserDataEntity;
import com.example.date.repository.TestDataRepository;

@Service
public class Login implements UserDetailsService {
	private TestDataRepository repository;

	public Login(TestDataRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDataEntity entity = repository.findByUsername(username)
				/*見つからなかった時のメッセージ*/
				.orElseThrow(() -> {
					System.out.println("NO:" + username);
					return new UsernameNotFoundException("Not Found");
				});

		return new User(
				entity.getUsername(),
				entity.getPassword(),
				List.of(new SimpleGrantedAuthority("ROLE_USER")));
	}

}
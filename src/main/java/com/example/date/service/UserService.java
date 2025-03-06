package com.example.date.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import jakarta.transaction.Transactional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.date.entity.UserDataEntity;
import com.example.date.repository.TestDataRepository;

@Service
public class UserService {
	private UserDetailsService userDetailsService;
	private TestDataRepository repository;
	public UserService(TestDataRepository repository,UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
		this.repository = repository;
	}
	@Transactional
	public void setData(UserDataEntity data)throws IOException{
		  //String uuid = UUID.randomUUID().toString();
		  data.setId(UUID.randomUUID().toString());
		  
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		  //String hashedPassword = encoder.encode(data.getPassword());
		  data.setPassword(encoder.encode(data.getPassword()));
		  System.out.println(data);
		  repository.save(data);
		  
		  //自動ログイン機能実装*/
		  UserDetails userDetails = userDetailsService.loadUserByUsername(data.getUsername());
		  Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), 
				  userDetails.getAuthorities());
		  SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	public List<UserDataEntity> getData() throws IOException{
		return repository.findAll();
	}
	
	public boolean isUserExists(String username) {
		return repository.existsByUsername(username);
	}
	
	public void deleteDate(String id) throws IOException{
		repository.deleteById(id);
	}
}

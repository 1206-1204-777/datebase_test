package com.example.date.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import jakarta.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.date.entity.UserDataEntity;
import com.example.date.repository.TestDataRepository;

@Service
public class UserService {
	private TestDataRepository repository;

	public UserService(TestDataRepository repository) {

		this.repository = repository;
	}

	@Transactional
	public void setData(UserDataEntity data) throws IOException {
		/*idの格納*/
		data.setId(UUID.randomUUID().toString());
		/*パスワードの格納*/
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		data.setPassword(encoder.encode(data.getPassword()));
		System.out.println(data);
		repository.save(data);

	}

	public List<UserDataEntity> getData() throws IOException {
		return repository.findAll();
	}

	public boolean isUserExists(String username) {
		return repository.existsByUsername(username);
	}

	public void deleteDate(String id) throws IOException {
		repository.deleteById(id);
	}
}

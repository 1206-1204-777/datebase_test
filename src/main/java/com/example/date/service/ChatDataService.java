package com.example.date.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.date.entity.ChatDataEntity;
import com.example.date.repository.ChatDataRepository;

@Service
public class ChatDataService {
	private ChatDataRepository chatRepository;

	public ChatDataService(ChatDataRepository chatRepository) {
		this.chatRepository = chatRepository;
	}

	public void saveChat(ChatDataEntity entity) throws IOException{
		chatRepository.save(entity);
	}
	
	public List<ChatDataEntity>getChat()throws IOException{
		return chatRepository.findAll();
	}
 }

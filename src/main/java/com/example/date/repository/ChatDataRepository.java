package com.example.date.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.date.entity.ChatDataEntity;

@Repository
public interface ChatDataRepository extends JpaRepository<ChatDataEntity,Long>{

}

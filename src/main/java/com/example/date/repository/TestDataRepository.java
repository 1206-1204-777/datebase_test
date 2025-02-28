package com.example.date.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.date.entity.DataTestEntity;

@Repository
public interface TestDataRepository extends JpaRepository<DataTestEntity,Long>{

}

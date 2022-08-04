package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Prod_type;

public interface prod_type extends JpaRepository<Prod_type, Integer> {
	@Query("select prodtData from Prod_type prodtData where prodtData.type_id = ?1")
	public Prod_type findbyid(int id);
}

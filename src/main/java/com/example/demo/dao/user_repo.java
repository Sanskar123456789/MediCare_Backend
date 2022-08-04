package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.user;

public interface user_repo extends JpaRepository<user, Integer>{
	@Query("select userdata from user userdata where userdata.email = ?1")
	public user findbyname(String s);

}

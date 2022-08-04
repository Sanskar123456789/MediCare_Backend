package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Product;

public interface prod_repo extends JpaRepository<Product, Integer> {
	@Query("select prodData from Product prodData where prodData.Prod_id = ?1")
	public Product findbyid(int id);
}

package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.models.orders;
import com.example.demo.models.user;

public interface order_repo extends JpaRepository<orders, Integer> {

	@Query("select orderdata from orders orderdata where orderdata.razorpay_id = ?1")
	public orders findbyOrderid(String id);
	
	
	@Query("select orderdata from orders orderdata where orderdata.User_id = ?1")
	public List<orders> getOrderDetailByUserEmail(user id);
	
	
}

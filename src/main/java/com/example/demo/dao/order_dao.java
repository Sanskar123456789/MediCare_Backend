package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.orders;
import com.example.demo.models.user;

@Service
public class order_dao {
	@Autowired
	order_repo obj;
	
	public orders create(orders e) {
		return obj.save(e);
	}
	
	public List<orders> read() {
		return obj.findAll();
	}

	public Optional<orders> read_one(int id)
	{
		return obj.findById(id);
	}
	
	public orders readbyOrderId(String id) {		
		return obj.findbyOrderid(id);
	}
	
	public orders update(orders e) {
		orders item = new orders();
		item.setRazorpay_id(e.getRazorpay_id());
		return obj.save(item);
	}
	
	public boolean delete(int id) {
		obj.deleteById(id);
		return true;
	}
	
	public int getOrderCount() {
		return (int) obj.count();
	}
	
	public List<orders> getOrderDetailByUserEmail(user id) {
		return obj.getOrderDetailByUserEmail(id);
	}
}

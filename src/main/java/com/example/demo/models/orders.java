package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int order_id;
	
	@ManyToMany
	
	private List<Product> items;
	
	private int total_price;
	private String razorpay_id;
	@OneToOne
	private user User_id;
	private boolean isPaid = false;

}

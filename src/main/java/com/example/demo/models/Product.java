package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Prod_id;
	private int price;
	@OneToOne
	private Prod_type type;
	private String description;
	private String image_url;
	private String prod_name;
	private String cloud_id;

}

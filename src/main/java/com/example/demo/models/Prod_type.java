package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Prod_type {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int type_id;
	private String type_name;

}

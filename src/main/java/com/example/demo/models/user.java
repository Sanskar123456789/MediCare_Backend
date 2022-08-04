package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class user {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	private String name;
	private int phone_no;
	private String Address;
	private String email;
	private boolean isAdmin;
	private String password;
	
}

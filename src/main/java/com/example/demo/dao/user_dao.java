package com.example.demo.dao;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.models.user;

@Service
public class user_dao implements UserDetailsService {
	Logger log = Logger.getAnonymousLogger();
	@Autowired
	user_repo obj;
	
	public user finduser(String s) {		
		 user e =  obj.findbyname(s);
		 return e;
	}
	
	public user create(user e) {
		return obj.save(e);
	}
	
	public List<user> read() {
		return obj.findAll();
	}

	public Optional<user> read_one(int id)
	{
		return obj.findById(id);
	}
	
	public user update(user e) {
		user item = new user();
		item.setAdmin(false);
		item.setEmail(e.getEmail());
		item.setName(e.getName());
		item.setPassword(e.getPassword());
		item.setPhone_no(e.getPhone_no());	
		item.setAddress(e.getAddress());
		return obj.save(item);
	}
	
	public boolean delete(int id) {
		obj.deleteById(id);
		return true;
	}

	@Override
	public com.example.demo.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("USERNAME0----->>" +email);
		user details = this.finduser(email);
		log.info(" User details " +details);
		if(details.getEmail()==null) {
			return new com.example.demo.UserDetails(null);
		}else {			
			return new com.example.demo.UserDetails(details);
		}
	}

}

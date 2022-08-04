package com.example.demo;

import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.demo.models.user;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String pass;
	private String auth;
	private String email;
	private int id;
	Logger log = Logger.getAnonymousLogger();
	
	public UserDetails(user user) {
		if(user==null) {			
			this.username = null;
			this.pass = null;
			this.id = 0;
			this.email = null;
			this.auth="ROLE_USER";
		}else {			
			this.username = user.getName();
			this.pass = user.getPassword();
			this.id = user.getUser_id();
			this.email = user.getEmail();
			if(user.isAdmin()) {
				log.info("ADMIN " +user.isAdmin());
				this.auth = "ROLE_ADMIN";
			}else {
				log.info("USER " +user.isAdmin());
				this.auth = "ROLE_USER";
			}
		}
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Arrays.asList(new SimpleGrantedAuthority(this.auth));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.pass;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String hasrole() {
		return auth;
	}
	
	public int hasid() {
		return id;
	}
	
	public String getEmail() {
		return this.email;
	}

}

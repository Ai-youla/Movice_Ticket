package com.example.movice_ticket.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {

	  private int  userId;
	  private String  userName;
	  private String  userPassword;
	  private String    picture;
	  private String  address;
	  private String  email;
	  private String  phone;
	  private String   userRole;
	public User() {
		 
	}
	public User(int userId, String userName, String userPassword, String picture, String address, String email,
                String phone, String userRole) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.picture = picture;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.userRole = userRole;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 String[] roles = userRole.split(",") ;
	        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
	        for(String role:roles) {
				authorities.add(new SimpleGrantedAuthority(role)) ;
	        }

			return authorities;
	}
	@Override
	public String getPassword() { 
		return userPassword;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	  
	  
}

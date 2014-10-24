package com.academysmart.model;

public class Employee {
	private String name;
	private String surName;
	private String email;
	private String id;
	
//	public Employee(){
//		
//	}
//	public Employee(String id, String name, String surName, String email){
//		this.name = name;
//		this.surName = surName;
//		this.email = email;
//		this.id = id;
//	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setSurName(String surName){
		this.surName = surName;
	}
	public String getSurName(){
		return surName;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return email;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}
}

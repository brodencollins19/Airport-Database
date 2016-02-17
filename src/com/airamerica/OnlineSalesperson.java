package com.airamerica;

public class OnlineSalesperson extends Person {
    
	public OnlineSalesperson(String personCode, String firstName,
			String lastName, Address address) {
		super(personCode, firstName, lastName, address);
	}
		
	public OnlineSalesperson(){
		this.personCode = "Online";
		this.firstName = "Null";
		this.lastName = "Online";
	}
	

	

}

package com.airamerica;

/* A partial implementation of address of a particular
 * Location */
public class Address {
	
	private String street;
	private String city;
	private String ZIP;
	private String country;
	private String state;
//TODO: Add more fields as needed

	/* Constructor - Generated using Eclipse Menu 
	 * (Source-> Generate Constructor using fields) */



	public Address(String street, String city, String state, String ZIP, String country) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.ZIP = ZIP;
		this.country = country;
		}
	
	public Address(){}

	/* Getters and Setters - Generated using Eclipse 
	 * Menu (Source-> Generate Getters and Setters) */	
	public String getStreet() {
		return street;
	}
	

	public void setStreet(String street) {
		this.street = street;
	}
	
	

	public String getCity() {
		return city;
	}
	

	public void setCity(String city) {
		this.city = city;
	}
	
	
	public String getZipCode() {
		return ZIP;
	}

	public void setZipCode(String ZIP) {
		this.ZIP = ZIP;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public String getState(){
		return state;
	}
	
	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	
	
	public void addAddress(){
		
	}
	
	public String cityState(){
		return this.getCity() + ", " + this.getState();
	}
	
	public void printAddress(){
		System.out.println(street + "," + city + ","  + state + ", " + ZIP + " " + country);
	}
	
	/* Additional methods as required */
}
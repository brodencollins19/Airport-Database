package com.airamerica;

public class GeneralCustomer extends Customer {
	
	private double tax;
	
	
	public GeneralCustomer(String customerCode, String type, String name){
		this.customerCode = customerCode;
		this.type = type;
		this.name = name;
		//this.primaryContact = primaryContact;
	}
	
	
	public GeneralCustomer(){
		
	}
	
	
	public String getCustomerCode() {
		return customerCode;
	}



	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}



	public String setName(){
		return name;
		
	}
    
	public void setName(String name){
		this.name = name;
	}



   
	public void setPrimaryContact(Person primaryContact) {
		this.primaryContact = primaryContact;
	}

	public Person getPrimaryContact(){
		return this.primaryContact;
	}

	public String getPrimaryContactCode() {
		return primaryContact.getPersonCode();
	}
	
	public void setAirlineMiles(int airlineMiles){
		this.airlineMiles = airlineMiles;
	}
	
	public void setAirlineMiles(String airlineMiles){
		this.airlineMiles = Integer.parseInt(airlineMiles);
	}
	
	

	public int getAirlineMiles(){
		return airlineMiles;
	}
	
	
	public void printCustomer(){
		System.out.println("Code :" + customerCode);
		System.out.println("Type: " + type);
		if(this.primaryContact != null){
			System.out.println("Primary Contact: " + this.primaryContact.getPersonCode());
		}
		else{}
		System.out.println("name: " + name);
		System.out.println("Airline Miles: " + airlineMiles);
		System.out.println("");
	}
	
	public String getCustomerName(){
		return this.name;
	}

	
	public String getType(){
		return "General";
	}

	public void setType(String type) {
		this.type = type;
		
	}


	
	

	





	
	
	

}
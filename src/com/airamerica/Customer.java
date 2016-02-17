package com.airamerica;

public abstract class Customer{	
	protected String customerCode;
	protected String name;
	protected String type;
	protected Person primaryContact;
	protected int airlineMiles;
	protected double tax;
	

	
	
	public abstract String getCustomerCode();

	public abstract void setCustomerCode(String customerCode);

	//public abstract String setName();
	
	public abstract void setName(String name);

	public abstract void setPrimaryContact(Person primaryContact);
	
	public abstract Person getPrimaryContact();
	
	public abstract String getPrimaryContactCode();
	
	public abstract void setAirlineMiles(String airlineMiles);
	
	public abstract int getAirlineMiles();
		
	public abstract void printCustomer();
	
	public abstract String  getCustomerName();
	
	public abstract String getType();
	
	
	//abstract double getTax();
	
	//abstract void set
	
}

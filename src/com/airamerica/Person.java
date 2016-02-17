package com.airamerica;
/*
/* A partial implementation representing a 
 * Person */
import java.util.ArrayList;
import java.util.List;

public class Person {
	
	protected String personCode;
	protected String firstName;
	protected String lastName;
	protected String phoneNo;
	protected SpecialAssistance assistance;
	
	/* Note how Address has been used (Composition Relationship) */ 
	private Address address;
	
	/* Note how email is used (a collection of variable size) */ 
	private List<String> emails;
	
	
	public Person(String personCode, String lastName, String firstName, Address address){
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		
		this.emails = new ArrayList<String>();
	}
	
	public Person(){}
	
	
	
	public String getPersonCode() {
		return personCode;
	}
	
	




	public SpecialAssistance getAssistance() {
		return assistance;
	}

	public void setAssistance(SpecialAssistance assistance) {
		this.assistance = assistance;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getPhoneNo() {
		return phoneNo;
	}




	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public Address getAddress() {
		return this.address;
	}
	
	public void setEmails(List<String> emails)
	{
		this.emails = emails;
	}
		
	// TODO: Add additional methods here
	public void addEmail(String email) {
		this.emails.add(email);
	}
	
	public void setAddress(Address address){
		this.address = address;
	}
	public void printPerson(){
		System.out.println("Code: " + personCode);
		System.out.println("Name: " + lastName + ", " + firstName);	
		System.out.print("Address: ");
		address.printAddress();
		System.out.println("Phone Number: " + phoneNo);
		System.out.print("Emails: ");
		for(int i=0;i<emails.size();i++){
			System.out.print(emails.get(i) + " ");
		}
	
	System.out.println("");
	System.out.println("");
	
		}
	}

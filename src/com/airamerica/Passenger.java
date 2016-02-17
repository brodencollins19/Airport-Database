package com.airamerica;

public class Passenger extends Person {
	private String seatNO;
	private String IDNumber;	private int age;
	private String nationality;
	
	
	public Passenger(String personCode, String firstName, String lastName,
			Address address, String seatNO, String IDNumber, int age, String nationality) {
		super(personCode, firstName, lastName, address);
		this.seatNO = seatNO;
		this.IDNumber = IDNumber;
		this.age = age;
		this.nationality = nationality;
		// TODO Auto-generated constructor stub
	}
	
	public Passenger(){
		
	}
	
	
	public String getSeatNO() {
		return seatNO;
	}
	
	public void setSeatNO(String seatNO) {
		this.seatNO = seatNO;
	}
	
	public void setAge(int age){
		this.age = age;
		
	}
	
	public String getIDNumber() {
		return IDNumber;
	}
	
	public int getAge() {
		return age;
	}
	
	
	
	public String getNationality() {
		return nationality;
	}
	
	public void setNationality(String nationailty){
		this.nationality = nationality;
	}
	
	public void setIDNumber(String IDNumber){
		this.IDNumber = IDNumber;
	}
	
	
	

}

package com.airamerica;

public class PassengerData  {
	private String seatNO;
	private String IDNumber;
	private int age;
	private String nationality;
	private Person passenger;		
	
	
	public PassengerData(String seatNO, String IDNumber, int age, String nationality, Person passenger){
		this.seatNO = seatNO;
		this.IDNumber = IDNumber;
		this.age = age;
		this.nationality = nationality;
		this.passenger = passenger;
	}
	
	public PassengerData(){
		
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
	
	
	
	
	
	public void setNationality(String nationality){
		this.nationality = nationality;
	}
	
	public void setIDNumber(String IDNumber){
		this.IDNumber = IDNumber;
	}
	
	
	public void setPassenger(Person passenger){
		this.passenger = passenger;
	}
	
	public Person getPassenger(){
		return this.passenger;
	}
	

}

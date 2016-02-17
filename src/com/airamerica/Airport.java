package com.airamerica;

public class Airport {
	private String airportCode;
	private String name;
	private Address address;
	private int latDegs;
	private int latMins;
	private int longDegs;
	private int longMins;
	private double passengerFacilityFee;
	private String location;
	
	public Airport(String airportCode, String name, Address address, int latDegs, int latMins, int longDegs, int longMins, double passengerFacilityFee){ 
		this.airportCode = airportCode;
		this.name = name;
		this.address = address;
		this.latDegs = latDegs;
		this.latMins = latMins;
		this.longDegs = longDegs;
		this.longMins = longMins;
		this.passengerFacilityFee = passengerFacilityFee;
	}
	
	public Airport(){
		
	}

	public String getAirportCode() {
		return airportCode;
	}
	

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}
	

	public void setAddress(Address address) {
		this.address = address;
	}
	

	public int getLatDegs() {
		return latDegs;
	}
	

	public void setLatDegs(int latDegs) {
		this.latDegs = latDegs;
	}
	

	public int getLatMins() {
		return latMins;
	}
	

	public void setLatMins(int latMins) {
		this.latMins = latMins;
	}
	

	public int getLongDegs() {
		return longDegs;
	}
	

	public void setLongDegs(int longDegs) {
		this.longDegs = longDegs;
	}
	

	public int getLongMins() {
		return longMins;
	}

	public void setLongMins(int longMins) {
		this.longMins = longMins;
	}

	public double getPassengerFacilityFee() {
		return passengerFacilityFee;
	}
	

	public void setPassengerFacilityFee(double passengerFacilityFee) {
		this.passengerFacilityFee = passengerFacilityFee;
	}
	
	
	public void setLocation(int latDeg, int latMin, int longDeg, int longMin){
		this.location = (Integer.toString(latDeg) + "°N" + Integer.toString(latMin) + "‘’" +
	                     Integer.toString(longDeg) + "°W" + Integer.toString(longMin)+ "‘’");
	}
	
	public String getLocation(){
		location = (Integer.toString(this.latDegs) + "°N" + Integer.toString(this.latMins) + "‘’" +
                Integer.toString(this.longDegs) + "°W" + Integer.toString(this.longMins)+ "‘’");
		return location;
	}
	
	public void printAirport(){
		System.out.println("Name: " + name);
		System.out.print("Address: ");
		address.printAddress();
		System.out.println("Location " + this.getLocation());
		System.out.println("Passenger Fee: $" + passengerFacilityFee);
		System.out.println("");
	}
}	
	

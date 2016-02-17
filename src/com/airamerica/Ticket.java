package com.airamerica;
import java.util.ArrayList;

import org.joda.time.DateTime;

import com.airamerica.utils.Haversine;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ticket")


public class Ticket extends Product {   //Defines ticket products
	protected String depAirportCode;
	protected Airport depAirportCity;                //Denote location of departure airport
	protected String arrAirportCode;
	protected Airport arrAirportCity;               //Denote location of arrival airport
	protected String depTime;
	protected String regDepTime;
	
	protected String arrTime;  //Time of arival and departure
	protected String regArrTime;
	
	protected DateTime travelDate;
	//protected DateTime arrDate;
	
	//protected Date arrivalTime;
	protected DateTime departureTime;
	
	protected String flightNo;
	protected String flightClass;
	protected String aircraftType;
	
	protected String getType(){
		return "";
	}
	
	
	protected ArrayList<PassengerData> passengers;
	
	//protected String[] seats;
	
	
	protected Insurance insurance = null;
	protected CheckedBaggage additionalBaggage = null;
	
	private String ticketNote;
	
	protected double discount;
	
	
	public Ticket(String ticketCode, String depAirportCode, String arrAirportCode, String depTime, String arrTime, String flightNo, String flightClass, String aircraftType){
		this.productCode = ticketCode;
		this.depAirportCode = depAirportCode;
		this.arrAirportCode = arrAirportCode;
		this.arrTime = arrTime;
		this.depTime = depTime;            
		this.flightNo = flightNo;
		this.flightClass = flightClass;
		this.aircraftType = aircraftType;
		this.passengers = new ArrayList<PassengerData>();
		
		
	}
	
	public Ticket(){
		this.passengers = new ArrayList<PassengerData>();
	}

	public String getDepAirportCode() {
		return depAirportCode;
	}

	public void setDepAirportCode(String depAirportCode) {
		this.depAirportCode = depAirportCode;
	}

	public String getArrAirportCode() {
		return arrAirportCode;
	}

	public void setArrAirportCode(String arrAirportCode) {
		this.arrAirportCode = arrAirportCode;
	}
	
	public void setTicketCode(String ticketCode){
		this.productCode = ticketCode;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public String getArrTime() {
		return arrTime;
	}

    public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		//this.aircraftType = aircraftType;
	}
	
	public String getTicketCode(){
		return super.getProductCode();
	}
	
	public void setTicketNote(String ticketNote){
		this.ticketNote = ticketNote;
	}
	
	public void setTravelDate(String travelDate){
		this.travelDate = DateTime.parse(travelDate);
	}
	
	
	public ArrayList<PassengerData> getPassengers(){
		return this.passengers;
	}
	
	
	
	
	//These next methods will compute various fees for a ticket
	   public double ComputeDistance(){  
		double lat1 = this.depAirportCity.getLatDegs() + (double)(this.depAirportCity.getLatMins())/60; 
		double lon1 = this.depAirportCity.getLongDegs() + (double)(this.depAirportCity.getLongMins())/60;
		double lat2 = this.arrAirportCity.getLatDegs() + (double)(this.arrAirportCity.getLatMins())/60;                                        
		double lon2 = this.arrAirportCity.getLongDegs() + (double)(this.arrAirportCity.getLongMins())/60;
		
		double distance = Haversine.getMiles(lat1, lon1, lat2, lon2);
		return distance;
	}
	
	
	
	public double ComputeBaseFare(){                          //Cost without taxes or fees
		if(this.getFlightClass().equalsIgnoreCase("BC")){
				return this.ComputeDistance() * 0.5;
		}
		else if(this.getFlightClass().equalsIgnoreCase("EP")){
			return this.ComputeDistance() * 0.2;
		}
		else{
			return this.ComputeDistance() * 0.15;
		}
		
	}
	
	int numPassengers = 0;
	public double getExciseTax(){  
		for(int i =0;i<this.passengers.size();i++)   {
			numPassengers++;
		}
		return 0.075 * (this.getSubtotal());
	}
	
	public double getFlightSegmentTax(){               //Flight Segment Tax
		return (4.0 * this.passengers.size());
	}
	
	public double getPassengerFacilityFee(){          //Passenger Facility fee
		return (this.passengers.size() * this.arrAirportCity.getPassengerFacilityFee());
	}
	
	public double getTaxes(){            //Returns total taxes for a ticket
		return this.getExciseTax() + this.getFlightSegmentTax() + this.get911SecurityFee() + this.getPassengerFacilityFee();
	}
	
	public double getFees(){           //Returns total fees for a ticket
		return (this.getPassengerFacilityFee() + this.get911SecurityFee());
	}
	
	public double get911SecurityFee(){            //911 Security ede
		return (this.getPassengers().size() * 5.60);
	}
	
	public double getSubtotal(){  //Subtotal with basefare
		return (this.ComputeBaseFare()*this.getPassengers().size());
	}
	//+ this.getFees()
	
	public double getTotal(){
		return((this.getSubtotal()) + (this.getTaxes()));
	}
	
	public void setDepartureAirport(Airport depAirportCity){
		this.depAirportCity = depAirportCity;
	}
	
	
	public void setArrivalAirport(Airport arrAirportCity){
		this.arrAirportCity = arrAirportCity;
	}
	
	public DateTime getTravelDate(){
		return this.travelDate;
	}
	
	@Override
	public void printProduct(){
		System.out.println("Product Code: " + super.getProductCode());
		System.out.println("Departure Airport: " + this.depAirportCode);
		System.out.println("Arrival Airport: " + this.arrAirportCode);
		System.out.println("Flight Number " + this.flightNo);
		System.out.println("Flight Class: " + this.flightClass);
		System.out.println("Aircraft Type: " + this.aircraftType);
		//System.out.println("Dep Time: " + this.depTime);
		
	}
	
	public Insurance getInsurance(){
		return this.insurance;
	}
	
	public void setInsurance(Insurance insurance){
		this.insurance = insurance;
	}
	
	
	
	public void setAdditionalBaggage(CheckedBaggage additionalBaggage){
		this.additionalBaggage = additionalBaggage;
	}
	
	public double getInsuranceCost(){
		return (this.ComputeDistance() * this.getInsurance().getCostPerMile())* this.passengers.size();
	}
	
	public double getInsuranceTax(){
		return 0.04 * this.getInsuranceCost();
	}
	
	public double getInsuranceTotal(){
		return this.getInsuranceCost() + this.getInsuranceTax();
		
	}
	
	public double getBaggageCost(){
		double firstBaggage = 25;
		double additionalBaggage = 0;
		if(this.additionalBaggage.getQuantity()>1){
			for(int i=1;i<this.additionalBaggage.getQuantity();i++){
				additionalBaggage += 35;
		
			}
			return firstBaggage + additionalBaggage;	
		}
		else{
			return firstBaggage;
		}
	}
	
	public double getBaggageTax(){
		return 0.04 * this.getBaggageCost();
	}
	
	public double getBaggageTotal(){
		return this.getBaggageTax() + this.getBaggageCost();
	}
	
	
	//Returns departurute and arrival times in standard (rather than military format)
	public String getRegDepTime(){
		String[] regularTime = this.depTime.split(":");
		
		int hours = Integer.parseInt(regularTime[0]);
		int depTimeHours;
		if(hours<12){
			depTimeHours = hours;
			return regularTime[0] + ":" + regularTime[1] + "AM";
			
		}
		else if(hours == 12){
			depTimeHours = hours;		
			return regularTime[0] + ":" + regularTime[1] + "PM";
		}
		else{
		    depTimeHours =  hours-12;
			regularTime[0] = String.valueOf(depTimeHours);
			return   regularTime[0] + ":" + regularTime[1]  + "PM";
		}
	}
	
	public String getRegArrTime(){
        String[] regularTime = this.arrTime.split(":");
		
		int hours = Integer.parseInt(regularTime[0]);
		int arrTimeHours;
		if(hours<12){
			arrTimeHours = hours;
			return regularTime[0] + ":" + regularTime[1] + "AM";
			
		}
		else if(hours == 12){
			arrTimeHours = hours;		
			return regularTime[0] + ":" + regularTime[1] + "PM";
		}
		else{
		    arrTimeHours =  hours-12;
			regularTime[0] = String.valueOf(arrTimeHours);
			return   regularTime[0] + ":" + regularTime[1] + "PM";
		}
	}
    //

	public String getTravelDay(){
		int travelDay= this.getTravelDate().getDayOfWeek();
		String dayOfWeek = "";
		switch(travelDay){
		    case 1:
		    	dayOfWeek = "Mon";
		    	break;
		    case 2:
		    	dayOfWeek = "Tues";
		    	break;
		    case 3:
		    	dayOfWeek = "Wed";
		    	break;
		    case 4:
		    	dayOfWeek = "Thurs";
		    	break;
		    case 5:
		    	dayOfWeek = "Fri";
		    	break;
		    case 6:
		    	dayOfWeek = "Sat";
		    	break;
		    case 7:
		    	dayOfWeek = "Sun";
		    	break;
		}
		
		return dayOfWeek;
	}

	public String getTicketNote() {
		return ticketNote;
	}

public CheckedBaggage getBaggage(){
	return this.additionalBaggage;
}

public String ArrDepTime() {
		// TODO Auto-generated method stub
		return null;
}


	
}
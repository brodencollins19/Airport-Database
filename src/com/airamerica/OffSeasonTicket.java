package com.airamerica;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import org.joda.time.DateTime;


@XStreamAlias("offSeasonTicket")
public class OffSeasonTicket extends Ticket {
	
	private DateTime seasonStartDate;
	private DateTime seasonEndDate;
	private double rebate;
	private final double serviceFee = 20.0;
	
	
	
	public OffSeasonTicket( String ticketCode, String depAirportCode,
			String arrAirportCode, String depTime, String arrTime,
			String flightNo, String flightClass, String aircraftType, String seasonStartDate, String seasonEndDate, double rebate) {
		super(ticketCode, depAirportCode, arrAirportCode, depTime, arrTime, flightNo,
				flightClass, aircraftType);
		
		this.seasonStartDate = DateTime.parse(seasonStartDate);
		this.seasonEndDate = DateTime.parse(seasonEndDate);
		this.rebate = rebate;
		
	}
	
	public OffSeasonTicket(){
		
	}
	
	@Override
	public String getType(){
		return "OffSeasonTicketTicket";
	}
	
	public void setSeasonStartDate(String seasonStartDate){
		this.seasonStartDate = DateTime.parse(seasonStartDate);
	}
	
	public void setSeasonStartDate(DateTime seasonStartDate){
		this.seasonStartDate = seasonStartDate;
	}
	
	public void setSeasonEndDate(DateTime seasonEndDate){
		this.seasonEndDate = seasonEndDate;
	}
	
	public DateTime getSeasonStartDate(){
		
		return seasonStartDate;
	}
     
	public void setSeasonEndDate(String seasonEndDate){
		this.seasonEndDate = DateTime.parse(seasonEndDate);
	}
	
	public DateTime getSeasonEndDate(){
		return seasonEndDate;
	}	      
	
	public void setRebate(double rebate){
		this.rebate = rebate;
	}
	
	public double getRebate(){
		return this.rebate;
	}
	
	public double getRebatePercentage(){
		if((this.getTravelDate().compareTo(this.seasonStartDate) > 0) && (this.getTravelDate().compareTo(this.seasonEndDate) < 0)){
			return (Math.round(this.rebate * 100.00)/100.00)*100.00;
		}
		
		else{
			return 0.00;
		}
		
	}
	
		 
	
	public void printProduct(){
		super.printProduct();
		System.out.println("Season Start: "  + this.seasonStartDate);
		System.out.println("Season End: " + this.seasonEndDate);
		System.out.println("Rebate: "  + this.rebate);
		System.out.println("");
	}
	
	
	@Override
	public double getFees(){
		return super.getFees() + this.serviceFee;
	}
	
	
	@Override
	public double ComputeBaseFare(){
		double baseFare = 0.0;
		double discount = super.ComputeBaseFare() * this.getRebate();
		
	    if((this.getTravelDate().compareTo(this.seasonStartDate) > 0) && (this.getTravelDate().compareTo(this.seasonEndDate) < 0)){
			baseFare= super.ComputeBaseFare() - discount;
		}
		else{
			baseFare = super.ComputeBaseFare();
		}
	 return baseFare;	
	}
	
	
	@Override
	public double getSubtotal(){  //Subtotal with basefare
		return (this.ComputeBaseFare()*this.getPassengers().size()) + this.serviceFee;
	}
	
	
	@Override
	public double getTotal(){
		return (this.getSubtotal() + this.getTaxes());
	}
	
	@Override
	public double getTaxes(){            //Returns total taxes for a ticket
		return this.getExciseTax() + this.getFlightSegmentTax() + this.get911SecurityFee() + this.getPassengerFacilityFee();
	}
	
	
	
    	 
		
		// TODO Auto-generated constructor stub
	}


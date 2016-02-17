package com.airamerica;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("awardTicket")
public class AwardTicket extends Ticket {
	private int pointsPerMile;
	private final double redemptionFee = 30.0;
	
	public AwardTicket(String ticketCode, String depAirportCode,
			String arrAirportCode, String depTime, String arrTime,
			String flightNo, String flightClass, String aircraftType, int pointsPerMile) {
		super(ticketCode, depAirportCode, arrAirportCode, depTime, arrTime, flightNo,
				flightClass, aircraftType);
		this.pointsPerMile = pointsPerMile;
	}
	
	
	public AwardTicket(){
		
	}
	
	public void setPointsPerMile(int pointsPerMile){
		this.pointsPerMile = pointsPerMile;
	}
	
	
	public int getPointsPerMile(){
		return pointsPerMile;
	}
	
	@Override
	public String getType(){
		return "AwardTicket";
	}
	
	public void printProduct(){
		super.printProduct();
		System.out.println("Points per Mile: " + pointsPerMile);
		System.out.println("");
	}
	
	
	public double ComputePointsFare(){
		return super.ComputeDistance() * this.getPointsPerMile();
			
	}

	
	@Override
	public double getFees(){
		return super.getFees() + this.redemptionFee;
	}
	
	public double getSubtotal(){  //Subtotal with basefare
		return (this.redemptionFee);
	}
	
	
	@Override
	public double getTotal(){
		return (this.getSubtotal() + this.getTaxes());
	}
	
	@Override
	public double getTaxes(){            //Returns total taxes for a ticket
		return this.getExciseTax() + this.getFlightSegmentTax() + this.get911SecurityFee() + this.getPassengerFacilityFee();
	}
	
}

package com.airamerica;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("standardTicket")
public class StandardTicket extends Ticket {

	public StandardTicket(String ticketCode, String depAirportCode,
			String arrAirportCode, String depTime, String arrTime,
			String flightNo, String flightClass, String aircraftType) {
		super(ticketCode, depAirportCode, arrAirportCode, depTime, arrTime, flightNo,
				flightClass, aircraftType);
	}
	
	public StandardTicket(){
		
	}
	
	@Override
	public void printProduct(){
		super.printProduct();
		System.out.println("");
	}
	
	@Override
	public String getType(){
		return "StandardTicket";
	}
	

}

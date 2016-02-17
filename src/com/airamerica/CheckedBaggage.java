package com.airamerica;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("checkedBaggage")
public class CheckedBaggage extends Service {
	private String ticketCode;
	private int quantity;
	
	
	public CheckedBaggage(String baggageCode, String ticketCode){
		this.productCode = baggageCode;
		this.ticketCode = ticketCode;
	}
	
	public CheckedBaggage(){
		
	}


	public String getTicketCode() {
		return ticketCode;
	}


	public void setTicketCode(String ticketCode) {
		this.ticketCode = ticketCode;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	
	public int getQuantity(){
		return this.quantity;
	}

	public void setTicketCode(int ticketCode) {
		this.ticketCode = String.valueOf(ticketCode);
		
	}
	

}

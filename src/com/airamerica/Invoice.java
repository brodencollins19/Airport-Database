package com.airamerica;
import java.io.PrintStream;
import java.util.ArrayList;

import org.joda.time.DateTime;



public class Invoice  {
	private String invoiceCode;
	private ArrayList<Ticket> tickets;
	private ArrayList<Service> services;
	private Customer customer;
	private Person salesperson;
	private DateTime invoiceDate;
	private String PNR;
	
	
	private   double finalTax = 0;
	private  double finalFee = 0;
	private   double finalSubtotal = 0;
	private  double finalTotal = 0;
	private  double finalDiscount = 0;
	
	
	
	
	
	
	
	public Invoice(String invoiceCode, String invoiceDate, String PNR){
		this.invoiceCode = invoiceCode;
		this.invoiceDate = DateTime.parse(invoiceDate);
		this.PNR = PNR;
	}
	
	public Invoice(){
		
	}

	
	
	public void setInvoiceDate(String invoiceDate){
		this.invoiceDate = DateTime.parse(invoiceDate);
	}
	
	
	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.tickets = tickets;
	}

	public ArrayList<Service> getServices() {
		return services;
	}

	public void setServices(ArrayList<Service> services) {
		this.services = services;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Person getSalesperson() {
		return salesperson;
	}

	public void setSalesperson(Person salesperson) {
		this.salesperson = salesperson;
	}

	
	public String getPNR() {
		return generatePNR();
	}

	public void setPNR(String pNR) {
		PNR = PNR;
	}


	
	
	public static String generatePNR() {
		char pnr[] = new char[6];
		for(int i=0;i<6;i++) {
			int num = (int)(Math.random() * 35) + 56;
			if(num<65) num=num-7;
			pnr[i] = (char)num;
		}
		return String.valueOf(pnr);
	}
	
	
	
	public void printInformation(){
		
	}
	
	public void printFees(){
		
	}
	//Method to generat Flight Information based on list of Tickets and its associated values
	public void printFlightInformation(){
		
		System.out.println("FLIGHT INFORMATION");
		System.out.printf("%-15s %-10s %-7s %-30s %-29s %-20s \n", "Day,Date", "Flight", "Class", "DepartureCity and Time", "ArrivalCity and Time", "Aircraft");
		for(int i =0;i<this.getTickets().size();i++){
		    System.out.printf("%-15s %-10s %-7s %-30s %-29s %-20s \n", this.tickets.get(i).getTravelDay() +","+ this.tickets.get(i).getTravelDate().toString("ddMMMyy"), this.tickets.get(i).getFlightNo(), this.tickets.get(i).getFlightClass(), this.tickets.get(i).depAirportCity.getAddress().cityState(), this.tickets.get(i).arrAirportCity.getAddress().cityState(), this.tickets.get(i).getAircraftType());  
			System.out.printf("%-15s %-10s %-7s %-30s %-29s %-20s \n", "","","", "("  + this.tickets.get(i).getDepAirportCode() + ")" + " " + this.tickets.get(i).getRegDepTime(), "(" + this.tickets.get(i).getArrAirportCode() + ")" + " " + this.tickets.get(i).getRegArrTime(), "");
			
			System.out.printf(" %-10s %-30s %-7s %-9s \n", "", "Traveler","Age", "SeatNO");
			
			//System.out.println("         Traveler                             Age    SeatNO");
			for(int j =0;j<this.tickets.get(i).getPassengers().size();j++){
				System.out.printf("%-11s %-30s %-7s %-9s \n", "", this.tickets.get(i).getPassengers().get(j).getPassenger().getLastName() +"," + this.tickets.get(i).getPassengers().get(j).getPassenger().getFirstName(), this.getTickets().get(i).getPassengers().get(j).getAge(), this.tickets.get(i).getPassengers().get(j).getSeatNO());
			}
			
			if(this.getTickets().get(i).getTicketNote().equals("")){
				System.out.printf("%28s \n",  this.tickets.get(i).getTicketNote());
			}
			else{
				System.out.printf("%28s \n", "*" + this.tickets.get(i).getTicketNote());
			}
			//System.out.printf("%28s \n",   this.tickets.get(i).getTicketNote());
		}

		System.out.println("--------------------------------------------------------------------------------------------------------");
	}
	
	//Method to print the information of a customer
	
	public void printCustomerInformation(){
		System.out.println("CUSTOMER INFORMATION");
		
		
		System.out.println("  " + this.getCustomer().getCustomerName() +  " " +"(" + this.getCustomer().getCustomerCode() + ")");
		System.out.println("  " + "[" + this.getCustomer().getType() + "]");
		System.out.println("  " + this.customer.getPrimaryContact().getLastName() +", " + this.customer.getPrimaryContact().getFirstName());
		System.out.println("  " + this.customer.getPrimaryContact().getAddress().getStreet());
		System.out.println("  " + this.getCustomer().getPrimaryContact().getAddress().getCity() + " " + this.getCustomer().getPrimaryContact().getAddress().getState() + " " + this.getCustomer().getPrimaryContact().getAddress().getZipCode() + " " + this.getCustomer().getPrimaryContact().getAddress().getCountry());
	
		System.out.printf("%-10s \n", "Salesperson: " + this.getSalesperson().getLastName() + "," + this.salesperson.getFirstName());
		System.out.println("--------------------------------------------------------------------------------------------------------");
	}
	
	public void setFinalFees(Double finalFee){
		this.finalFee = finalFee;
	}
	
	public double getFinalFee(){
		return this.finalFee;
		
	}
	
	public void setFinalDiscount(Double finalDiscount){
		this.finalDiscount = finalDiscount;
	}
	
	public double getFinalDiscount(){
		return finalDiscount;
	}
	
	
	public double getFinalTax(){
		return this.finalTax;
	}
	
	public void setFinalTax(double finalTax){
		this.finalTax = finalTax;
	}
	
	public double getFinalTotal(){
		return this.finalTotal;
		
	}
	public void setFinalTotal(double finalTotal){
		this.finalTotal = finalTotal;
	}
	public void printFareInformation(){
		System.out.println("FARES AND SERVICES");
		System.out.printf("%-10s %-68s %11s %10s %17s \n", "Code", "Item", "SubTotal", "Tax", "Total");
		int numPassengers = 0;
		
	    
		
		for(int i =0;i<this.tickets.size();i++){
			for(int j =0;j<this.getTickets().get(i).getPassengers().size();j++){
			    numPassengers++;
			}
		     
			if(this.getTickets().get(i) instanceof StandardTicket){
				
				System.out.printf("%-10s %-67s %-3s %-11s %-3s %-12s %-2s %-12s \n", this.tickets.get(i).getTicketCode(), this.getTickets().get(i).getType() + "(" + this.getTickets().get(i).getFlightClass() +") " + this.getTickets().get(i).getDepAirportCode() + " to " + this.getTickets().get(i).getArrAirportCode() + " " +"("+((Math.round(this.tickets.get(i).ComputeDistance()*100.00)/100.00) + " miles)"), "$", Math.round(100.00*this.getTickets().get(i).getSubtotal())/100.00,"$",Math.round(100.00*this.getTickets().get(i).getTaxes())/100.00,"$", Math.round(100.00*this.getTickets().get(i).getTotal())/100.00); 
				System.out.printf("%-10s %-65s \n", "", "(" +this.getTickets().get(i).getPassengers().size() + " units @ $" + Math.round(100.00*this.getTickets().get(i).ComputeBaseFare())/100.00 + "/unit)");
						
				finalTax += this.getTickets().get(i).getTaxes();
				finalTotal += this.getTickets().get(i).getTotal();
				finalSubtotal += this.getTickets().get(i).getTotal();
			}
			
			if(this.getTickets().get(i) instanceof OffSeasonTicket){
				System.out.printf("%-10s %-67s %-3s %-11s %-3s %-12s %-2s %-12s \n", this.tickets.get(i).getTicketCode(), this.getTickets().get(i).getType() + "(" + this.getTickets().get(i).getFlightClass() +") " + this.getTickets().get(i).getDepAirportCode() + " to " + this.getTickets().get(i).getArrAirportCode() + " " + "("+((Math.round(this.tickets.get(i).ComputeDistance()*100.00)/100.00) + " miles) " + ((OffSeasonTicket) this.tickets.get(i)).getRebatePercentage() + "% off"), "$", Math.round(100.00*this.getTickets().get(i).getSubtotal())/100.00 ,"$",Math.round(100.00*this.getTickets().get(i).getTaxes())/100.00,"$", Math.round(100.00*this.getTickets().get(i).getTotal())/100.00); 
				System.out.printf("%-10s %-65s \n", "", "(" +this.getTickets().get(i).getPassengers().size() + " units @ $" + Math.round(100.00*this.getTickets().get(i).ComputeBaseFare())/100.00 + "/unit) with $20.00 fee");			
				
				finalTax += this.getTickets().get(i).getTaxes();
				finalTotal += this.getTickets().get(i).getTotal();
				finalSubtotal += this.getTickets().get(i).getTotal();
			}
			
			if(this.getTickets().get(i) instanceof AwardTicket){
				System.out.printf("%-10s %-67s %-3s %-11s %-3s %-12s %-2s %-12s \n", this.tickets.get(i).getTicketCode(), this.getTickets().get(i).getType() + "(" + this.getTickets().get(i).getFlightClass() +") " + this.getTickets().get(i).getDepAirportCode() + " to " + this.getTickets().get(i).getArrAirportCode() + " " +"("+((Math.round(this.tickets.get(i).ComputeDistance()*100.00)/100.00) + " miles)"), "$", this.tickets.get(i).getSubtotal(),"$",Math.round(100.00*this.getTickets().get(i).getTaxes())/100.00,"$", Math.round(100.00*this.getTickets().get(i).getTotal())/100.00); 
				System.out.printf("%-10s %-65s \n", "", "(" +this.getTickets().get(i).getPassengers().size() + " units @ " + (Math.round(100.00*(((AwardTicket) this.getTickets().get(i)).ComputePointsFare())/100.00)/(this.tickets.get(i).getPassengers().size())) + " reward miles/unit)");
				
				finalTax += this.getTickets().get(i).getTaxes();
				finalTotal += this.getTickets().get(i).getTotal();
				finalSubtotal += this.getTickets().get(i).getTotal();
			
			}
			
			
				if (this.tickets.get(i).getInsurance() != null){
					
					System.out.printf("%-10s %-67s %-3s %-11s %-3s %-12s %-2s %-12s \n", this.tickets.get(i).getInsurance().getInsuranceCode(), "Insurance " + this.tickets.get(i).getInsurance().getName() +"(" + this.tickets.get(i).getInsurance().getAgeClass() +")","$",Math.round(100.00*this.tickets.get(i).getInsuranceCost())/100.00,"$",Math.round(100.00*this.tickets.get(i).getInsuranceTax())/100.00,"$",Math.round(100.00*this.tickets.get(i).getInsuranceTotal())/100.00);
					System.out.printf("%-10s %-65s \n", "", "("+this.tickets.get(i).getPassengers().size() + " units @ $" + this.tickets.get(i).getInsurance().getCostPerMile() +" perMile x " + Math.round(100.00*this.tickets.get(i).ComputeDistance())/100.00 + " miles)");
					
					finalTax += this.getTickets().get(i).getInsuranceTax();;
					finalTotal += this.getTickets().get(i).getInsuranceTotal();
					finalSubtotal += this.getTickets().get(i).getInsuranceCost();
					
				}
				
				if(this.tickets.get(i).getBaggage() != null){
					System.out.printf("%-10s %-67s %-3s %-11s %-3s %-12s %-2s %-12s \n", this.tickets.get(i).getBaggage().getProductCode(), "Baggage " +"(" + this.tickets.get(i).getBaggage().getQuantity() + " units @ $25.00 for 1st and $35 onwards)","$",Math.round(100.00*this.tickets.get(i).getBaggageCost())/100.00 +"0","$",Math.round(100.00*this.tickets.get(i).getBaggageTax())/100.00+"0","$",Math.round(100.00*this.tickets.get(i).getBaggageTotal())/100.00+"0");
					finalTax += this.getTickets().get(i).getBaggageTax();;
					finalTotal += this.getTickets().get(i).getBaggageTotal();
					finalSubtotal += this.getTickets().get(i).getBaggageCost();
				}
				
				for(int j=0;j<this.tickets.get(i).getPassengers().size();j++){
					if(this.tickets.get(i).getPassengers().get(j).getPassenger().getAssistance() != null)
					System.out.printf("%-10s %-67s %-3s %-11s %-3s %-12s %-2s %-12s \n", this.tickets.get(i).getPassengers().get(j).getPassenger().getAssistance().getProductCode(),   "Special Assisstance for [" + "(" +this.tickets.get(i).passengers.get(j).getPassenger().getLastName() + "," + this.tickets.get(i).passengers.get(j).getPassenger().getFirstName() +"] " + "("+this.tickets.get(i).passengers.get(j).getPassenger().getAssistance().getType()+")" ,"$","0","$","0","$","0");
				}
				
			}
		for(int i=0;i<this.services.size()-1;i++){
			if(this.services.get(i) instanceof Refreshment){
				System.out.printf("%-10s %-67s %-3s %-11s %-3s %-12s %-2s %-12s \n", this.services.get(i).getProductCode(),  ((Refreshment) this.services.get(i)).getName() + "("+((Refreshment) this.services.get(i)).getQuantity()+" units @ $ " + ((Refreshment) this.services.get(i)).getCost()+"/unit w/ 5% off","$",Math.round(100.00*((Refreshment) this.services.get(i)).getCost())/100.00 * this.tickets.get(i).passengers.size() +"0","$",Math.round(100.00*((Refreshment) this.services.get(i)).getTax())/100.00+"0","$",Math.round(100.00*((Refreshment) this.services.get(i)).getTotal())/100.00+"0");
				finalTax += ((Refreshment) this.services.get(i)).getTax();;
				finalTotal += ((Refreshment) this.services.get(i)).getTotal();
				finalSubtotal += ((Refreshment) this.services.get(i)).getCost();
			}
			
		}
		
	    double discount;
		double fee;
		String discountString = ""; //A
			
		System.out.println("                                                                              ====================================");
		//This next one is the total cost list: sub, tax, total
		System.out.printf("%-10s %-67s %-3s %-11s %-3s %-12s %-2s %7s \n", "SubTotals", "","$",Math.round(100.00*finalSubtotal)/100.00,"$",Math.round(100.00*finalTax)/100.00,"$",Math.round(100.00*finalTotal)/100.00);
		if(this.getCustomer() instanceof CorporateCustomer){
			fee = 40.0;
			discount = .12 * this.finalSubtotal;
			discountString = "DISCOUNT ( 12.00% of SUBTOTAL )";
		}
		else if(this.getCustomer() instanceof GovernmentCustomer){
			fee = 0.0;
			discount = this.finalTax;		
			discountString = "DISCOUNT ( NO TAX )";
		}
		else{
			fee = 0.0;
			discount = 0.00;
			discountString = "( None ) ";
		}
			
		System.out.printf("%-111s %-2s %7s \n", "Discount " + discountString,"$",Math.round(100.00*(-(discount)))/100.00);
		System.out.printf("%-111s %-2s %7s \n", "Additional Fees","$",Math.round(100.00*fee)/100.00);
		
		//finalDiscount = discount;
		//finalTotal = finalTotal;
		//finalTax = finalTax;
		//finalFee = fee;
		
		
		//this.setFinalTotal(finalTax);
		
			  
		
		 
		
		
		
		
		
		System.out.printf("%-111s %-2s %7s \n","TOTAL","$",Math.round(100.00*((finalTotal+fee+finalTax) - discount))/100.00);
		fee = 0.0;
		finalTotal = 0.0;
		finalSubtotal =0.0;
		finalTax = 0.0;
		discount = 0.0;
	}
	//finalTax = 0;
	
	
	
	
	 
	   
	public void printSummary(){
		finalTax = this.getFinalTax();
		//System.out.println(finalTax);
		//System.out.printf("%-10s %-45s %-65s %-30s %-15s %-8s %-20s \n",this.getInvoiceCode(),this.customer.getCustomerName() + "["+this.getCustomer().getType()+"]",this.getSalesperson().getLastName()+", " +this.getSalesperson().getFirstName());
		System.out.printf("%-10s %-45s %-65s %-30s %-15s %-8s %-20s \n",this.getInvoiceCode(),this.customer.getCustomerName() + "["+this.getCustomer().getType()+"]",this.getSalesperson().getLastName()+", " +this.getSalesperson().getFirstName(),"$" + finalFee,"$" + finalTax,"$" + finalDiscount, "$" + this.getFinalTotal());
				
		} 
	
	
	//Prints the complete summary for one invoice object
	public void printInvoice(){
		//System.out.println(discountTotal);
		System.out.println(this.invoiceCode);
		System.out.println("--------------------------------------------------------------------------------------------------------");
		System.out.println("AIR America                                                                            PNR");
		System.out.println("IssueDate: " + this.invoiceDate.toString("MMM dd,yyyy") + "                                                              " + this.getPNR());
		
		System.out.println("--------------------------------------------------------------------------------------------------------");
		this.printFlightInformation();
		this.printCustomerInformation();
		this.printFareInformation();
		//this.setFinalDiscount(0.0);
		//this.setFinalFees(0.0);
		//this.setFinalTax(0.0);
		//this.setFinalTotal(0.0);
		System.out.println("-----------------------------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("");	

	
	}
	
	
		
	
}

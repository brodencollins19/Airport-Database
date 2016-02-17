package unl.cse.assignments;
import com.airamerica.utils.*;
import com.airamerica.interfaces.InvoiceData;



public class tester {
     
     
	
	public static void main(String args[]){
		
		
		
		
	InvoiceData.removeAllPersons();
	InvoiceData.removeAllCustomers();
	
	InvoiceData.addPerson("abcd", "Broden", "Collins","4027304086" , "6711 Ridge Rd", "Lincoln", "NE", "68512", "USA");
	InvoiceData.addCustomer("C69", "V", "abcd", "Broseph Stalin", 50000);
//InvoiceData.addStandardTicket("aaaa", "dep", "arr", "1:22", "2:50", "69abc", "e", "death star");
	InvoiceData.addInsurance("abab", "Geico", "Gilf", 69);
	InvoiceData.addSpecialAssistance("lol", "heelies");
	InvoiceData.addInvoice("INV001", "C0006", "aaaaa", "2011-11-11");
	InvoiceData.addTicketToInvoice("INV006",
			"abcd", "1233:2:12", "blah");
	
	
	
	
	
	
	
	
	}
}

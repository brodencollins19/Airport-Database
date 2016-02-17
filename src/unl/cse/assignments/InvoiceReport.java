package unl.cse.assignments;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.airamerica.*;
import com.airamerica.utils.CustomList;



public class InvoiceReport {


	//static CustomList<Invoice> invoices = DatabaseReader.DBInvoiceReader(); //Used to test if Reading from flat files
	static ArrayList<Invoice> invoices = DataConverter.InvoiceReader();       //Use if reading from database
	
	

	private static void generateSummaryReport() {
		

		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		System.out.printf("%-15s %-45s %-60s %-30s %-10s %-10s %-10s \n", "Invoice","Customer","SalesPerson","Fees","Taxes","Discounts","Total");
		
		for(int i=0;i<invoices.size();i++){
			invoices.get(i).printSummary();
		}
		

		// TODO: Add code for generating summary of all Invoices

		//return sb.toString();
	}

	private String getTravelSummary() {
		StringBuilder sb = new StringBuilder();
		sb.append("FLIGHT INFORMATION");
		sb.append("==================================================\n");
		

		// TODO: Add code for generating Travel Information of an Invoice

		return sb.toString();

	}

	private String getCostSummary() {

		StringBuilder sb = new StringBuilder();
		sb.append("FARES AND SERVICES");
		sb.append("==================================================\n");

		// TODO: Add code for generating Cost Summary of all
		// products and services in an Invoice

		return sb.toString();

	}

	public String generateDetailReport() {
		StringBuilder sb = new StringBuilder();
		sb.append("Individual Invoice Detail Reports\n");
		sb.append("==================================================\n");

		/*
		 * TODO: Loop through all invoices and call the getTravelSummary() and
		 * getCostSummary() for each invoice
		 */

		return sb.toString();
	}

	public static void main(String args[]) {
		int i = 0;

		InvoiceReport ir = new InvoiceReport();
		generateSummaryReport();
		
		
		System.out.println("\n\n");
		System.out.println("Individual Invoice Detail Reports");
		
		System.out.println("Initial Order");
		System.out.println("");
		
		System.out.println("==================================================");
		

		System.out
				.println("======================================================================================================================");
		System.out.println("\n\n");
		System.out.println("");
		for(i=0;i<invoices.size();i++){
			invoices.get(i).printInvoice();
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
	
		
		
		
		
		
		
	}
}

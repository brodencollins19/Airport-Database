package com.airamerica;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("refreshment")
public class Refreshment extends Service {
	private String name;
	private double cost;
	private int quantity;
	
	public Refreshment(String refreshmentCode, String name, double cost){
		this.productCode = refreshmentCode;
		this.name = name;
		this.cost = cost;
	}
	
	public Refreshment(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		//double discount = 0.05*this.cost;
		//return cost-discount;
		return this.cost;
	}
	
	public double getTax(){
		return (this.getCost() * 0.04);
	}
	
	public double getTotal(){
		return this.getCost() + this.getTax();
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public void printProduct(){
		System.out.println(super.getServiceCode());
		System.out.println("Name: " + this.name);
		System.out.println("Cost: " + this.cost);
		System.out.println("");
	}

	
}

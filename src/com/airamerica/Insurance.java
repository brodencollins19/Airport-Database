package com.airamerica;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("insurance")
public class Insurance extends Service {
	private String name;
	private String ageClass;
	private int lowerAge;
	private int upperAge;
	private double costPerMile;
	private int quantity;
	
	public Insurance(String insuranceCode, String name, String ageClass, double costPerMile){
		this.productCode = insuranceCode;
		this.name = name;
		this.ageClass = ageClass;
		this.costPerMile = costPerMile;
		this.lowerAge = Integer.valueOf(ageClass.charAt(0));
		this.upperAge = Integer.valueOf(ageClass.charAt(2));
	}
	
	public Insurance(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAgeClass() {
		return ageClass;
	}

	public void setAgeClass(String ageClass) {
		this.ageClass = ageClass;
	}

	public double getCostPerMile() {
		return costPerMile;
	}

	public void setCostPerMile(double costPerMile) {
		this.costPerMile = costPerMile;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
		
	}
	
	public double getQuantity(){
		return this.quantity;
	}
	
	
	
	public String getInsuranceCode(){
		return this.productCode;
	}

}

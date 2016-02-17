package com.airamerica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
	protected String productCode; //Main superclass to define a product
	private final double salesTax = 0.04;
	
	
	public String getProductCode(){
		return this.productCode;
	}
	
	public void printProduct(){
		System.out.println("Product Code: " + this.getProductCode());
	}

	public void setProductCode(String productCode){
		this.productCode = productCode;
	}
}

package com.airamerica;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("service")
public class Service extends Product {        //Defines products that are service based
	
	protected final double salesTax = 0.04;
	
	public String getServiceCode(){
		return super.getProductCode();
	}
	
	
	
	

}

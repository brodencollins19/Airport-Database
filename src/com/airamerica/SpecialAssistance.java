package com.airamerica;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("specialAssistance")
public class SpecialAssistance extends Service {
	private String type;
	
	public SpecialAssistance(String assitanceCode,String type){
		this.productCode = assitanceCode;
		this.type = type;
	}
	
	public SpecialAssistance(){
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}

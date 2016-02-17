package com.airamerica;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XStreamAlias("products")
public class Products {
        @XStreamImplicit
	private ArrayList<Product> products;

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	
}

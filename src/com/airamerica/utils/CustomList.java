package com.airamerica.utils;

import java.util.Comparator;

import com.airamerica.Invoice;



public class CustomList<T>{
	
	private int size;
	private Object[] items;
	int i = 0;
	
	public CustomList(){
		items = new Object[1];
		size = 0;
	}
	
	
	public void add(T obj){
		if(size<items.length){
			items[size] = obj;
		}
		else{
			Object[] old = items;
			items = new Object[size+1];
			for(i=0;i<size;i++){
				items[i] = old[i];
			}
			old = null;
			items[size] = obj;
		}
		size++;
	}
	
	public void add(int index, T obj){
		if(index == size){
			Object[] old = items;
			items = new Object[size+1];
			for(i=0;i<size;i++){
				items[i] = old[i];
			}
			old = null;
			items[size] = obj;
			size++;
		}
		else if(index < size){
			Object[] old = items;
			items = new Object[size+1];
			
			for(i=0;i<index;i++){
				items[i] = old[i];
			}
			
			items[index] = obj;
			
			for(i=index+1;i<size+1;i++){
				items[i] = old[i-1];
			}
			old = null;
			size++;
		}
	}
	
	public void remove(int index){         //removes items at a given index
		if(index<size){
			Object[] old = items;
			items = new Object[size-1];
			for(i=0;i<index;i++){
				items[i] = old[i];
			}
			
			for(i=index;i<size-1;i++){
				items[i] = old[i+1];
			}
			
			old = null;
			size--;
		}
		
	}
	
	public void removeAll(){
		
	}
	
	public T get(int index){                  //return an object(Invoice) at a specific index in the list
		Object listItem = null;
		try{
			listItem = items[index];
		}catch(IndexOutOfBoundsException Expt){
			Expt.printStackTrace();
		}
		return ((T)listItem);
	}
	
	
	public int size(){
		return items.length;
	}
	
	
	
	public static Comparator<Invoice> byCustomerName(){
		Comparator<Invoice> byCustomerName = new Comparator<Invoice>(){

			@Override
			public int compare(Invoice inv1, Invoice inv2) {
				return inv1.getCustomer().getCustomerName().compareToIgnoreCase(inv2.getCustomer().getCustomerName());
			}
		};
		return byCustomerName;
	}
	
	
	public static Comparator<Invoice> bySalespersonName(){
		Comparator<Invoice> bySalespersonName = new Comparator<Invoice>(){

			@Override
			public int compare(Invoice inv1, Invoice inv2) {
				if(inv1.getCustomer().getType().equalsIgnoreCase(inv2.getCustomer().getType())){
					if(inv1.getSalesperson().getLastName().equalsIgnoreCase(inv2.getSalesperson().getLastName())){
						return inv1.getSalesperson().getFirstName().compareToIgnoreCase(inv2.getSalesperson().getFirstName());
					}
					else{
						return inv1.getSalesperson().getLastName().compareToIgnoreCase(inv2.getSalesperson().getLastName());
					}
				}
				else{
					return inv1.getCustomer().getType().compareToIgnoreCase(inv2.getCustomer().getType());
				}
			}
	};
	
	return bySalespersonName;
  }		
}
			
			
				
			
	
	
	
	
	
	
	
	
		
	
	


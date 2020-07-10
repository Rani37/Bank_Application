


package com.dxc.pojos;

import java.io.Serializable;

public class Transaction implements Serializable
{
	private String name;
	private double balance;
	public Transaction() 
	{
		
	}
	public Transaction(String name, double balance) {
		super();
		this.name = name;
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	

}

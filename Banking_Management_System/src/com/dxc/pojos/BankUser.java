
package com.dxc.pojos;

public class BankUser 
{
	private int aNumber;
	private String aName;
	private double aBalance;
	private String s;
	
	public BankUser()
	{
		
	}

	public BankUser(int aNumber, String aName, double aBalance)
	{
		super();
		this.aNumber = aNumber;
		this.aName = aName;
		this.aBalance = aBalance;
	}
	
	

	public BankUser(int aNumber, String aName) {
		super();
		this.aNumber = aNumber;
		this.aName = aName;
	}
	

	public BankUser(double aBalance) {
		super();

		this.aBalance = aBalance;
	}
	
	public int getaNumber() {
		return aNumber;
	}

	public void setaNumber(int aNumber) {
		this.aNumber = aNumber;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public double getaBalance() {
		return aBalance;
	}

	public void setaBalance(double aBalance) {
		this.aBalance = aBalance;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}
	
	
	
	

}

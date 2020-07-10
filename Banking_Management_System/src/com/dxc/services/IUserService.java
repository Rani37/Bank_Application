
package com.dxc.services;

import java.util.List;

import com.dxc.pojos.BankUser;
import com.dxc.pojos.Transaction;

public interface IUserService 
{
	public boolean login(int u,String p);
	public boolean depositAmount(int a, double b);
	public boolean withdrawAmount(int a, double b);
	public double getBalance(int a);
	public boolean changePassword(int a, String n, String cp);
	public boolean transferAmount(int accNo,int acc,double bal);
	public List<Transaction>printMiniStatement(int a);

	

}

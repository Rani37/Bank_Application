
package com.dxc.services;

import java.util.List;

import com.dxc.dao.IUserDao;
import com.dxc.dao.UserDaoImpl;
import com.dxc.pojos.BankUser;
import com.dxc.pojos.Transaction;

public class UserServiceImpl implements IUserService
{
	IUserDao dao = new UserDaoImpl();
	
	public boolean login(int u,String p) 
	{
		return dao.login(u, p);
	}
	public boolean depositAmount(int a, double b)
	{
		return dao.depositAmount(a, b);
	}
	public boolean withdrawAmount(int a, double b)
	{
		return dao.withdrawAmount(a, b);
	}
	public double getBalance(int a)
	{
		return dao.getBalance(a);
	}
	public boolean changePassword(int a, String n, String cp)
	{
		return dao.changePassword(a, n, cp);
	}
	public boolean transferAmount(int accNo,int acc,double bal)
	{
		return dao.transferAmount(accNo, acc, bal);
	}
	public List<Transaction>printMiniStatement(int a)
	{
		return dao.printMiniStatement(a);
	}
}

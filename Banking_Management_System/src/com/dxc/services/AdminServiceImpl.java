
package com.dxc.services;

import java.util.List;

import com.dxc.dao.AdminDaoImpl;
import com.dxc.dao.IAdminDao;
import com.dxc.pojos.BankUser;

public class AdminServiceImpl implements IAdminService
{
	IAdminDao dao = new AdminDaoImpl();
	
	public boolean loginCheck(String u, String p) 
	{
		return dao.loginCheck(u, p);
	}
	public boolean addCustomer(int i, String n,double b,String pass)
	{
		return dao.addCustomer(i,n,b,pass);
	}
	public List<BankUser> getAllCustomers()
	{
		return dao.getAllCustomers();
	}
	public boolean searchCustomer(int a)
	{
		return dao.searchCustomer(a);
	}
	public void modifyCustomer(int accNo,String name)
	{
		 dao.modifyCustomer(accNo,name);
	}
	public List<BankUser> getCustomer(int acc)
	{
		return dao.getCustomer(acc);
	}
	public boolean closeAccount(int acc)
	{
		return dao.closeAccount(acc);
	}

	public double getBalance(int a)
	{
		return dao.getBalance(a);
	}
	public boolean update(int acc) 
	{
		return dao.update(acc);
	}
}

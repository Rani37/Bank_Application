
package com.dxc.services;

import java.util.List;

import com.dxc.pojos.BankUser;

public interface IAdminService 
{
	public boolean loginCheck(String u, String p);
	public boolean addCustomer(int i, String n,double b,String pass);
	public List<BankUser> getAllCustomers();
	public boolean searchCustomer(int a);
	public void modifyCustomer(int accNo,String name);
	public List<BankUser> getCustomer(int acc);
	public boolean closeAccount(int acc);
	public  double getBalance(int a);
	public boolean update(int acc);


}

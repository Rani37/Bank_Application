
package com.dxc.dao;
import java.sql.*;
import java.util.*;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.dxc.pojos.BankUser;

public class AdminDaoImpl implements IAdminDao
{
		private static Connection conn;

	static
	{
		 
		try                 
		  {
		  Class.forName("com.mysql.jdbc.Driver");
		  System.out.println("Driver Loaded...");
		  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankapp?autoReconnect=true&useSSL=false", "root", "@123");
		  System.out.println("Connected to database..");
		  }
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
	
	}
				
				public boolean loginCheck(String u, String p) 
				{
					try
					{

						 Statement stmt = conn.createStatement();
						 ResultSet rset = stmt.executeQuery("select adminname,password from admin");
						
						 while(rset.next())
						 {
							 if(rset.getString(1).equals(u) && rset.getString(2).equals(p))
							 {
								 return true;
							 }
							 break;
						 }
					}
					catch (Exception e) 
					{
						System.out.println("Authentication Error Occured!!!");
						e.printStackTrace();
					}
					return false;
					
				}
				
				public boolean addCustomer(int i,String n,double b,String p)
				{
					try
					 {
						 PreparedStatement pstmt = conn.prepareStatement("insert into customer values(?,?,?,?)");	
						 
						  pstmt.setInt(1,i);
						  pstmt.setString(2,n); 
						  pstmt.setDouble(3,b);
						  pstmt.setString(4,p);

						  pstmt.executeUpdate(); 
						  return true;
					 }
					 catch (Exception e) 
					 {
						e.printStackTrace();
					 }
					return false;
				}
				
				public List<BankUser> getAllCustomers()
				{
					List<BankUser> list = new ArrayList<>();
					
					try 
					{
						Statement stmt=conn.createStatement();
						ResultSet rs=stmt.executeQuery("select * from customer");
						while(rs.next())
						{
							list.add(new BankUser(rs.getInt(1),rs.getString(2),rs.getInt(3)));
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					return list;
					
				}
				
				public boolean searchCustomer(int a) 
				{
					try
					{
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("select * from customer");
						
						while(rs.next())
						{
							if(a==rs.getInt(1))
							{
								return true;
							}
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					return false;
				}
				
				public List<BankUser> getCustomer(int acc)
				{
						List<BankUser> list = new ArrayList<>();
					
					try 
					{
						PreparedStatement pstmt = conn.prepareStatement("select * from customer where accountnumber=?");
						pstmt.setInt(1,acc);
						ResultSet rs = pstmt.executeQuery();
						while(rs.next())
						{
							list.add(new BankUser(rs.getInt(1),rs.getString(2),rs.getInt(3)));
						}
					}catch(Exception e)
					{
						e.printStackTrace();
					}
					return list;
					
				}
				
				public boolean update(int acc)
				{
					try 
					{
						
						Statement stmt = conn.createStatement();
						PreparedStatement pstmt1 = conn.prepareStatement("select accountnumber from customer where accountnumber=?");
						pstmt1.setInt(1,acc);
						ResultSet rs = pstmt1.executeQuery();
						System.out.println(acc);

						while(rs.next())
						{
							if(rs.getInt(1)==acc)
							{
								int a = rs.getInt(1);
								System.out.println("hi-"+a);
								return true;
							}
							else
							{
								return false;
							}
						}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
						return false;
				}

				
				
				public void modifyCustomer(int accNo,String name)
				{
					try 
					{
						PreparedStatement pstmt=conn.prepareStatement("update customer set name=? where accountnumber=?");
						pstmt.setString(1,name);	
						pstmt.setInt(2,accNo);
						pstmt.executeUpdate();
					}
					catch(Exception e) {
						e.printStackTrace();
					}
			
				}
				public boolean closeAccount(int acc)
				{
					try
					{	Statement stmt = conn.createStatement();
						PreparedStatement pstmt = conn.prepareStatement("delete from customer where accountnumber=?");
						pstmt.setInt(1,acc);
						if(pstmt.execute())
						{
							return false;
						}
					}
					catch(Exception e)
					{
					  e.printStackTrace();
					}
					return true;
				}
				
				public double getBalance(int a)
				{
					
					double balance=0;

					try
					{
						PreparedStatement pstmt = conn.prepareStatement("select accountbalance from customer where accountnumber=?");
						pstmt.setDouble(1,a);
						ResultSet rset = pstmt.executeQuery();
						
						while(rset.next())
						{
							balance=rset.getDouble(1);
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					return balance;
					
				}

				
	
	
	
	
	
	
	
}

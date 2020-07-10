
package com.dxc.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.dxc.pojos.BankUser;
import com.dxc.pojos.Transaction;

public class UserDaoImpl implements IUserDao
{
	Transaction t = new Transaction();
	
	int count=0,b,var;
	
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
	
		
			public boolean login(int u,String p) 
			{
					int flag=0;
				try
				{
					 Statement stmt = conn.createStatement();
					 ResultSet rset = stmt.executeQuery("select * from customer");
					
					 System.out.println(u);
					 System.out.println(p);
					 
					 while(rset.next())
					 {
						 if(rset.getInt(1)==u && rset.getString(4).equals(p))
						 {
							 flag=1;
							 System.out.println("hai");
							 return true;
						 }
					 }
				}
				catch (Exception e) 
				{
					System.out.println("Authentication Error Occured!!!");
					e.printStackTrace();
				}
				return false;
			}
			
			public boolean depositAmount(int a, double b)
			{
				double balance=0;
				String s = "deposit";
				try 
				{
					
					PreparedStatement pstmt=conn.prepareStatement("select * from customer where accountnumber=?");
					pstmt.setInt(1,a);
					ResultSet rs = pstmt.executeQuery();
					rs.next();
					
						balance=rs.getDouble(3);
						System.out.println(balance);
						balance=balance+b;
						System.out.println(balance);
					
					pstmt.close();
					PreparedStatement pstmt1=conn.prepareStatement("update customer set accountbalance=? where accountnumber=?");
					pstmt1.setDouble(1,balance);
					pstmt1.setInt(2, a);
					pstmt1.executeUpdate();
					
					PreparedStatement pstmt2=conn.prepareStatement("insert into transactions values(?,?,?)");
					pstmt2.setInt(1,a);
					pstmt2.setString(2,s);
					pstmt2.setDouble(3,b);
					pstmt2.executeUpdate(); 
					
					return true;

				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return false;
			}
			
			public boolean withdrawAmount(int a, double b)
			{
				double amount=0;
				String s = "withdraw";
				try 
				{
					
					PreparedStatement pstmt=conn.prepareStatement("select * from customer where accountnumber=?");
					pstmt.setInt(1,a);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next())
					{
						amount=rs.getDouble(3);
						System.out.println(amount);
						System.out.println(b);
						if(amount>b)
						{
							amount=amount-b;
							System.out.println(amount);
						}
						else
						{
							return false;
						}
					}
					PreparedStatement pstmt1=conn.prepareStatement("update customer set accountbalance=? where accountnumber=?");
					pstmt1.setDouble(1,amount);
					pstmt1.setInt(2, a);
					pstmt1.executeUpdate();
					
					PreparedStatement pstmt2=conn.prepareStatement("insert into transactions values(?,?,?)");
					pstmt2.setInt(1,a);
					pstmt2.setString(2,s);
					pstmt2.setDouble(3,b);
					pstmt2.executeUpdate(); 
					
					return true;

				}
				catch(Exception e) {
					e.printStackTrace();
				}
				return false;
			}
			
			public double getBalance(int a)
			{
				double balance=0;
				try
				{
					PreparedStatement pstmt = conn.prepareStatement("select accountbalance from customer where accountnumber=?");
					pstmt.setDouble(1,a);
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next())
					{
						balance=rs.getDouble(1);
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return balance;
				
			}
			
			public boolean changePassword(int a, String n, String cp) 
			{
				System.out.println(n.equals(cp));
				try
				{
					if(n.equals(cp))
					{
						PreparedStatement pstmt = conn.prepareStatement("update customer set password=? where accountnumber=?");
						pstmt.setString(1,cp);
						pstmt.setInt(2,a);
						pstmt.execute();
						return true;
					}
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			return false;
			}
			
			
			public boolean transferAmount(int accNo, int acc,double bal)
			{
				double amount1=0.0,balance=bal;
				double amount2 =0.0;
				String s  ="transfer";
				try
				{
					PreparedStatement pstmt = conn.prepareStatement("select * from customer where accountnumber=?");
					pstmt.setDouble(1,accNo);
					ResultSet rset = pstmt.executeQuery();
					rset.next();
					
					amount1 = rset.getDouble(3);
					System.out.println("Before transfer:"+amount1);
					if(amount1>balance)
					{
						amount1 = amount1-balance;
						System.out.println("After:"+amount1);
					}
					else
					{
						return false;
					}
					PreparedStatement pstmt1=conn.prepareStatement("update customer set accountbalance=? where accountnumber=?");
					pstmt1.setDouble(1,amount1);
					pstmt1.setInt(2, accNo);
					pstmt1.executeUpdate();
					
					PreparedStatement pstmt2 = conn.prepareStatement("select * from customer where accountnumber=?");
					pstmt2.setDouble(1,acc);
					ResultSet rs = pstmt2.executeQuery();
					rs.next();
					amount2 = rs.getDouble(3);
					
					System.out.println("amount 2 - "+amount2);
					amount2 = amount2+balance;
					System.out.println("amount 2 - "+amount2);
					
					PreparedStatement pstmt3=conn.prepareStatement("update customer set accountbalance=? where accountnumber=?");
					pstmt3.setDouble(1,amount2);
					pstmt3.setInt(2, acc);
					pstmt3.executeUpdate();
					
					PreparedStatement pstmt4=conn.prepareStatement("insert into transactions values(?,?,?)");
					pstmt4.setInt(1,accNo);
					pstmt4.setString(2,s);
					pstmt4.setDouble(3,balance);
					pstmt4.executeUpdate(); 
					
					return true;
						
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				return false;
			}
			
			
			
			public List<Transaction>printMiniStatement(int a)
			{
				List<Transaction> list = new ArrayList<>(); 
	
				
				
				try
				{
					PreparedStatement pstmt = conn.prepareStatement("select * from transactions where accountnumber=?");
					pstmt.setInt(1,a);
					ResultSet rs = pstmt.executeQuery();
					while(rs.next())
					{
						count=count+1;
					}
			
					var=count-5;
					PreparedStatement pstmt1 = conn.prepareStatement("select * from transactions where accountnumber=?");
					pstmt1.setInt(1,a);
					ResultSet rset = pstmt.executeQuery();
					
					while(rset.next())
					{
						b=b+1;
		
						if(b>var)
						{
							list.add(new Transaction(rset.getString(2),rset.getDouble(3)));
						}
	
					}
					System.out.println(list);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				return list;
			}

}
	
	
						

package com.dxc.controllers;



import java.io.IOException;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.BankUser;
import com.dxc.pojos.Transaction;
import com.dxc.services.IUserService;
import com.dxc.services.UserServiceImpl;

/*import javafx.collections.transformation.FilteredList;*/

@WebServlet("/user")
public class UserServlet extends HttpServlet 
{
	IUserService userService = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String message="";
		String action = "";
		
		
		String temp=request.getParameter("btn");
		
		if(temp!=null)
			action = request.getParameter("btn");
		
		BankUser u = new BankUser();
	
		
		HttpSession session = request.getSession();
		
		
		
			if(action.equals("login"))
			{
				int id = Integer.parseInt(request.getParameter("user"));
				session.setAttribute("id",id);
				String password = request.getParameter("pass");
				
				
				
				boolean b = userService.login(id,password);
				if(b)
				{
					message="Login successfull";
					session.setAttribute("message", message);
					response.sendRedirect("usermenu.jsp");
				}
				else
				{
					message="Incorrect Login Credentials";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
				}
				
			}
			else if(action.equalsIgnoreCase("deposit"))
			{
				int accNo = Integer.parseInt(request.getParameter("num"));
				double amount = Double.parseDouble(request.getParameter("amt"));
				
				boolean b = userService.depositAmount(accNo,amount);
				if(b)
				{
					message="Amount Deposited Successfully";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
				}
				else
				{
					message="Account not found to deposit";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
				}
			}
			else if(action.equals("withdraw"))
			{
				int accNo = (int)session.getAttribute("id");
				double amount = Double.parseDouble(request.getParameter("amt"));
				
				boolean b = userService.withdrawAmount(accNo,amount);
				System.out.println(b);
				if(b)
				{
					message="Amount withdrawn Successfully";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
				}
				else
				{
					message="Insufficent Balance";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
				}
			}
			else if(action.equals("get balance"))
			{
				int accNo = (int)session.getAttribute("id");
				double bal = userService.getBalance(accNo);
				session.setAttribute("list",bal);
				response.sendRedirect("getbalance.jsp");
			}
			else if(action.equals("changepassword"))
			{
				int id = (int)session.getAttribute("id");
				String newPass = request.getParameter("new");
				String cp = request.getParameter("cp");
				
				boolean change = userService.changePassword(id,newPass,cp);
				System.out.println(change);
				
				if(change)
				{
					message = "Password changed successfully";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
				}
				else
				{
					message = "Password incorrect";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
					
				}
			}
			else if(action.equalsIgnoreCase("transfer_amount")) 
			{
				int accNo = (int)session.getAttribute("id");
				int acc = Integer.parseInt(request.getParameter("num"));
				double balance = Double.parseDouble(request.getParameter("amount"));
				
				boolean transfer = userService.transferAmount(accNo,acc,balance);
				
				if(transfer)
				{
					message = "Transaction successfull";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
				}
				else
				{
					message="Insufficent Balance to transfer";
					session.setAttribute("message", message);
					response.sendRedirect("view.jsp");
				}
			}
			else
			{
				int accNo = (int)session.getAttribute("id");
				List<Transaction> list = userService.printMiniStatement(accNo);
				session.setAttribute("mini",list);
				response.sendRedirect("ministatement.jsp");
				
			}
		
	}

	
}



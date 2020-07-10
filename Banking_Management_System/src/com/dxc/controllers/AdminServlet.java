
package com.dxc.controllers;

import java.io.IOException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dxc.pojos.BankUser;
import com.dxc.services.AdminServiceImpl;
import com.dxc.services.IAdminService;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet 
{		

	 IAdminService adminService = new AdminServiceImpl();
	 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
			String username = request.getParameter("user");
			String password = request.getParameter("pass");
			
			boolean b = adminService.loginCheck(username,password);
			if(b)
			{
				message="Login successfull!!";
				session.setAttribute("message", message);
				response.sendRedirect("show.jsp");
			}
			else
			{
				message="Incorrect Login Credentials";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
			}
			
		}
		
		
		else if(action.equals("Add Customer"))
		{
			String name = request.getParameter("name");
			int accNo = Integer.parseInt(request.getParameter("number"));
			double balance = Double.parseDouble(request.getParameter("balance"));
			String pass = request.getParameter("pass");
			
			boolean b1 = adminService.addCustomer(accNo,name,balance,pass);
			if(b1)
			{
				session.setAttribute("message", "Customer added successfully");
				response.sendRedirect("view.jsp");
			}
			else
			{
				message=" Error Occured";
				session.setAttribute("message", message);
				response.sendRedirect("view.jsp");
			}
		}
		else if(action.equals("search"))
		{
			int accNo = Integer.parseInt(request.getParameter("num"));
			session.setAttribute("accNo",accNo);
			boolean status = adminService.searchCustomer(accNo);
			if(status)
			{
				response.sendRedirect("dispcustomers.jsp");
			}
			else
			{
				message="Customer not found";
				session.setAttribute("message",message);
				response.sendRedirect("view.jsp");
			}
		}
		
		else if(action.equals("Account detailes"))
		{
			int accNo = (int) session.getAttribute("accNo");
			System.out.println(accNo);
			List<BankUser> list = adminService.getCustomer(accNo);
			session.setAttribute("list",list);
			response.sendRedirect("showcustomers.jsp");
		}
		else if(action.equals("update"))
		{
			 int accNo = Integer.parseInt(request.getParameter("number"));
			 session.setAttribute("accNo",accNo);
			 boolean update = adminService.update(accNo);
			 if(update)
			 {
				 response.sendRedirect("update.jsp");
			 }
			 else
			 {
				 session.setAttribute("message","Account not ");
				 response.sendRedirect("view.jsp");
			 }
			 
			
		}
		else if(action.equals("modify_customer"))
		{

		  	int accNo = (int) session.getAttribute("accNo");
			String name = request.getParameter("name");
			adminService.modifyCustomer(accNo,name);
			message = "Successfully modified";
			session.setAttribute("message",message);
			response.sendRedirect("view.jsp");
		
				
		}
		else if(action.equals("close_account"))
		{
			int accNo = Integer.parseInt(request.getParameter("num"));
			System.out.println(accNo);
			boolean close = adminService.closeAccount(accNo);
			System.out.println(close);
			if(close)
			{
				message = "Account closed!! ";
				session.setAttribute("message",message);
				response.sendRedirect("view.jsp");
			}
			else
			{
				message = "Account not found ";
				session.setAttribute("message",message);
				response.sendRedirect("view.jsp");
			}

		}
		
		else if(action.equals("get_balance"))
		{
			int accNo = Integer.parseInt(request.getParameter("bal"));
			double bal = adminService.getBalance(accNo);
			session.setAttribute("list",bal);
			response.sendRedirect("getbalance.jsp");

		}
		else 
		{
			List<BankUser> list = adminService.getAllCustomers();
			session.setAttribute("list",list);
			response.sendRedirect("showcustomers.jsp");
		}
	}


}

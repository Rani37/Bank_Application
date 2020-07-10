<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style >


table, tr, td
					{
						 border: 1px solid black;
						 width : 40%;
						 text-align: center;
					}
				</style>
<meta charset="ISO-8859-1">
<title>ministatement</title>
</head>

		<style>
					table, tr, td
					{
						 border: 1px solid black;
						 width : 40%;
						text-align: center; 
					}
					table.center 
					{
						margin-left: auto;
  						margin-right: auto;
  						
					}
  
					
				</style>
<body bgcolor="#FFFF99">


		<c:forEach var="statement" items="${mini}">
								<table class="center">
							<tr>
								<td>
									<h5><c:out value="${statement.getName()}"></c:out></h5>
								</td>
								<td>
									<h5><c:out value="${statement.getBalance()}"></c:out></h5>
								</td>	
							</tr>
						</table>
					</c:forEach>		

</body>
</html>

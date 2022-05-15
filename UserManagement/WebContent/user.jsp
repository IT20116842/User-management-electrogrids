<%@ page import="com.userManage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/validUser.js"></script> 
</head>
<body>

		<div class="container"> 
		<div class="row">  
			<div class="col"> 
				<h1>User Management</h1>
				<br>
					<form id="formUser" name="formUser" method="post" action="user.jsp">  
						User Name:  
	 	 				<input id="userName" name="userName" type="text"  class="form-control form-control-sm">
						<br>User Address:   
	  					<input id="userAddress" name="userAddress" type="text" class="form-control form-control-sm" >   
	  					<br>User NIC:   
	  					<input id="userNIC" name="userNIC" type="text"  class="form-control form-control-sm">
						<br>User Email:
						<input id="userEmail" name="userEmail" type="text"  class="form-control form-control-sm">
						<br>User Phone Number:
						<input id="userPNO" name="userPNO" type="text"  class="form-control form-control-sm">
						<br>
						
						<div id="alertSuccess" class="alert alert-success"> </div>				
				   		<div id="alertError" class="alert alert-danger"></div>
				   		 
						<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
						<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value=""> 
					</form>
					
					
				   <br>
					<div id="user">
						<%
						userManage userObj = new userManage();
						out.print(userObj.readUser());
						%>
					</div>
					
					 
				</div>
			</div>
	</div>

</body>
</html>
<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" 
	rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" 
	crossorigin="anonymous">
<script src="Components/jquery-3.6.0.js" type="text/javascript"></script>
<script src="Components/user.js" type="text/javascript"></script>
<style type="text/css">
#divUsersGrid{  padding-top:20px; padding-left: 50px; background-color: #f2db9b;}
.frm{ padding-top:20px; padding-right: 50px; background-color: #ede1a8; border-right: 1px solid grey; }
</style>
<title>User Management</title>
</head>
<body style="background-color:#e6edd1; padding-top: 20px;">
	<div class="container" style="box-shadow: 1px 15px 30px #404037; background-color:#f2ffcf;">
	<h1 style="margin:20px 0px 20px 400px; padding-top: 10px;">User Management</h1>
	<hr>
	<div class="row">
			<div class="col-4 frm">				
				<form id="formUser" name="formUser" action="">
					User NIC: <input id="nic" name="nic" type="text"
						class="form-control form-control-sm"> <br> 
						
					User Name: <input id="name" name="name" type="text"
						class="form-control form-control-sm"> <br> 
						
					User Address: <input id="address" name="address" type="text"
						class="form-control form-control-sm"> <br> 
												
					User Contact: <input id="contact" name="contact" type="text"
						class="form-control form-control-sm"> <br> 
						
						<input style="width: 50%;" id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> 
						
						<input type="hidden" id="hidUserIDSave" name="hidUserIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				</div>
				<div class="col-8"  id="divUsersGrid">
					<%
					User userObj = new User();
					out.print(userObj.readUsers());
					%>
				</div>
		</div>
		</div>
</body>
</html>

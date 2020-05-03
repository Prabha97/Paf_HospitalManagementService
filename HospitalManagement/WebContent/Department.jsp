<%@page import="model.Department"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Departments Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.2.1.min.js"></script> 
<script src="Components/sub.js"></script> 
</head> 
<body> 
<div class="container"> 
<div class="row"> 
<div class="col-6">  
	<h3 align="center">Add Departments To Hospitals</h3>
 
 	<form id="formDepartment" name="formDepartment" action="Department.jsp"> 
 	  
 		Hospital ID:   
 		<input id="hospitalId" name="hospitalId" type="text"        
 				class="form-control form-control-sm">
 				
 		<br>Department Name:   
 		<input id="departmentName" name="departmentName" type="text"        
 				class="form-control form-control-sm"> 
 
  		<br> Head of Department:   
  		<input id="departmentHead" name="departmentHead" type="text"
  		        class="form-control form-control-sm"> 
 
 		<br> Staff Vacancies:   
 		<input id="depStaffVacan" name="depStaffVacan" type="text"
 		        class="form-control form-control-sm"> 
 
  		<br>   
  		<input id="btnSave" name="btnSave" type="button" value="Save"
  		         class="btn btn-primary">
  		<input type="hidden" id="hidDepartmentIDSave"
  		         name="hidDepartmentIDSave" value="">  
  	</form> 
 
 	<div id="alertSuccess" class="alert alert-success"></div>  
 	<div id="alertError" class="alert alert-danger"></div> 

 	<br> 
 	<div id="divDepartmentssGrid">
 			<%    
					Department depObj = new Department();    
 				    out.print(depObj.readDepartments());   
 			%> 
 	</div>
 </div>   
 </div>  
 </div>   
 </body> 
 </html> 
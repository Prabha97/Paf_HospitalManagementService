<%@page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.2.1.min.js"></script> 
<script src="Components/main.js"></script> 
</head> 
<body> 
<div class="container"> 
<div class="row"> 
<div class="col-6">  
	<h3 align="center">Hospitals Registration Page</h3>
 
 	<form id="formHospital" name="formHospital" action="Hospital.jsp">   
 		Hospital Name:   
 		<input id="hospitalName" name="hospitalName" type="text"        
 				class="form-control form-control-sm"> 
 
  		<br> Hospital Address:   
  		<input id="hospitalAddress" name="hospitalAddress" type="text"
  		        class="form-control form-control-sm"> 
 
 		<br> Hospital City:   
 		<input id="hospitalCity" name="hospitalCity" type="text"
 		        class="form-control form-control-sm"> 
 
  		<br> Hospital Phone Number:   
  		<input id="phoneNum" name="phoneNum" type="text"
  		        class="form-control form-control-sm"> 
 
  		<br> Hospital Email:   
  		<input id="hospitalEmail" name="hospitalEmail" type="text"        
  		class="form-control form-control-sm"> 
 
  		<br> Hospital Description:   
  		<input id="hospitalDesc" name="hospitalDesc" type="text"
  		        class="form-control form-control-sm"> 
 
  		<br> Hospital Open Hours:   
  		<input id="openHours" name="openHours" type="text"
  		        class="form-control form-control-sm">
 
  		<br>   
  		<input id="btnSave" name="btnSave" type="button" value="Save"
  		         class="btn btn-primary">
  		<input type="hidden" id="hidHospitalIDSave"
  		         name="hidHospitalIDSave" value="">  
  	</form> 
 
 	<div id="alertSuccess" class="alert alert-success"></div>  
 	<div id="alertError" class="alert alert-danger"></div> 

 	<br> 
 	<div id="divHospitalsGrid">
 			<%    
					Hospital hospObj = new Hospital();    
 				    out.print(hospObj.readHospitals());   
 			%> 
 	</div>
 </div>   
 </div>  
 </div>   
 </body> 
 </html> 
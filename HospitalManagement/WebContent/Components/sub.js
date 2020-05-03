$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateDepartmentAddingForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidDepartmentIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "departmentsAPI",
		type : t,
		data : $("#formDepartment").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onDepartmentsSaveComplete(response.responseText, status);
		}
	});
}); 

function onDepartmentsSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
					
			$("#divDepartmentssGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while saving.");
		$("#alertError").show();
	}
	$("#hidDepartmentIDSave").val("");
	$("#formDepartment")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidDepartmentIDSave").val($(this).closest("tr").find('#hidDepartmentIDUpdate').val());     
	$("#hospitalId").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#departmentName").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#departmentHead").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#depStaffVacan").val($(this).closest("tr").find('td:eq(3)').text());
});

//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "departmentsAPI",
		type : "DELETE",
		data : "Department_ID=" + $(this).data("departmentid"),
		dataType : "text",
		complete : function(response, status)
		{
			onDepartmentDeletedComplete(response.responseText, status);
		}
	});
});

function onDepartmentDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divDepartmentssGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error while deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateDepartmentAddingForm() {  
	// HospitalID  
	var hosid = $("#hospitalId").val().trim();  
	if (!$.isNumeric(hosid))  {   
		return "Insert Hospital ID here.";  
	} 

	// Dep name  
	if ($("#departmentName").val().trim() == "")  
	{  
		return "Insert the Name of the Department.";  
	}
	
	//Dep Head ID is Numerical value
	var dhead = $("#departmentHead").val().trim();  
	if (!$.isNumeric(dhead))  {   
		return "Insert Doctor ID here.";  
	} 

	// number of vacancies
	var vacan = $("#depStaffVacan").val().trim();  
	if (!$.isNumeric(vacan))  {   
		return "Insert Number of Vacancies here.";  
	} 
	
	return true;
}
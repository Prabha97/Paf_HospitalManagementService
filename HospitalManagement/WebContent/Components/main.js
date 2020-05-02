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
	var status = validateHospitalForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	$("#formHospital").submit(); 
}); 

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());     
	$("#hospitalName").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#hospitalAddress").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#hospitalCity").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#phoneNum").val($(this).closest("tr").find('td:eq(3)').text());
	$("#hospitalEmail").val($(this).closest("tr").find('td:eq(4)').text());     
	$("#hospitalDesc").val($(this).closest("tr").find('td:eq(5)').text());     
	$("#openHours").val($(this).closest("tr").find('td:eq(6)').text());
});

//CLIENTMODEL========================================================================= 
function validateHospitalForm() {  
	// Name  
	if ($("#hospitalName").val().trim() == "")  
	{   
		return "Insert Name of the Hospital.";  
	} 

	// Address  
	if ($("#hospitalAddress").val().trim() == "")  
	{  
		return "Insert Hospital Address.";  
	}
	
	// City  
	if ($("#hospitalCity").val().trim() == "")  
	{   
		return "Insert City Where Hospital is located.";  
	} 

	// Phone  
	if ($("#phoneNum").val().trim() == "")  
	{  
		return "Insert Phone number of the Hospital.";  
	}
	
	// Email  
	if ($("#hospitalEmail").val().trim() == "")  
	{   
		return "Insert Email Address of the Hospital.";  
	} 

	// Description  
	if ($("#hospitalDesc").val().trim() == "")  
	{  
		return "Insert Small Description about Hospital.";  
	}
	
	// Open Hours  
	if ($("#openHours").val().trim() == "")  
	{  
		return "Insert Hospital Open Hours.";  
	}
	
	//is Numerical value
	var openHour = $("#openHours").val().trim();  
	if (!$.isNumeric(openHour))  {   
		return "Insert a numerical value for Hours.";  
	} 
	return true;
}
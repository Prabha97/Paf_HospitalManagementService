$(document).ready(function() 
{  
	$("#alertSuccess").hide();  
	$("#alertError").hide(); 
});

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear status msges-------------  
	$("#alertSuccess").text(""); 
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide();
	

	// Form validation----------------  
	var status = validateHospitalForm(); 
	
	// If not valid-------------------  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	}
	
	// If valid----------------------  
//	var hosp = getHospitalCard($("#hospitalName").val().trim();    
//			//$('input[name="rdoGender"]:checked').val(),       
//			//$("#ddlYear").val());  
//			//$("#colStudents").append(student);    
//	$("#alertSuccess").text("Saved successfully.");  
//	$("#alertSuccess").show();    
//	$("#formHospital")[0].reset(); 
});
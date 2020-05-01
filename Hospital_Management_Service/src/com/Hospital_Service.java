package com;

import model.Hospital;

import javax.annotation.security.RolesAllowed;
//For REST Service 
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON 
import com.google.gson.*;

import bean.HospitalBean;

//For XML 
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Hospital") 
public class Hospital_Service {  
	Hospital hospitalObj = new Hospital();
	
	@RolesAllowed({"patient"})
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readHospitals()  {   
		return hospitalObj.readHospitals();
	}
	
	@RolesAllowed({"admin"})
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertHospitals(String HospitalData) {
		// Convert the input string to a JSON object
		JsonObject hosjosnObj = new JsonParser().parse(HospitalData).getAsJsonObject();
	
		HospitalBean hosBean = new HospitalBean();
		
		hosBean.setHospital_Name(hosjosnObj.get("Hospital_Name").getAsString());
		hosBean.setHospital_Address(hosjosnObj.get("Hospital_Address").getAsString());
		hosBean.setHospital_City(hosjosnObj.get("Hospital_City").getAsString());
		hosBean.setHospital_Phone(hosjosnObj.get("Hospital_Phone").getAsString());
		hosBean.setHospital_Email(hosjosnObj.get("Hospital_Email").getAsString());
		hosBean.setHospital_Description(hosjosnObj.get("Hospital_Description").getAsString());
		hosBean.setOpen_Hours(hosjosnObj.get("Open_Hours").getAsInt());
		
		// Read the values from the JSON object
		String output = hospitalObj.insertHospitals(hosBean);
		return output;

	}
	
	@RolesAllowed({"admin"})
	@PUT 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateHospitals(String HospitalsData) { 
		//Convert the input string to a JSON object  
		JsonObject hospitalObject = new JsonParser().parse(HospitalsData).getAsJsonObject(); 
		HospitalBean hosUpdate_bean = new HospitalBean();
		
		 //Read the values from the JSON object  
		hosUpdate_bean.setHospital_ID(hospitalObject.get("Hospital_ID").getAsInt());
		hosUpdate_bean.setHospital_Name(hospitalObject.get("Hospital_Name").getAsString());
		hosUpdate_bean.setHospital_Address(hospitalObject.get("Hospital_Address").getAsString());
		hosUpdate_bean.setHospital_City(hospitalObject.get("Hospital_City").getAsString());
		hosUpdate_bean.setHospital_Phone(hospitalObject.get("Hospital_Phone").getAsString());
		hosUpdate_bean.setHospital_Email(hospitalObject.get("Hospital_Email").getAsString());
		hosUpdate_bean.setHospital_Description(hospitalObject.get("Hospital_Description").getAsString());
		hosUpdate_bean.setOpen_Hours(hospitalObject.get("Open_Hours").getAsInt());
		 
		String output = hospitalObj.updateHospitals(hosUpdate_bean); 
		 
		return output; 
	}

	@RolesAllowed({"admin"})
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospitals(String HospitalsData) {  
		// Convert the input string to a JSON object 
		JsonObject doc = new JsonParser().parse(HospitalsData).getAsJsonObject();   
		HospitalBean hosDel_bean = new HospitalBean();
		
		//Read the value from the element <Hospital_ID>  
		hosDel_bean.setHospital_ID(doc.get("Hospital_ID").getAsInt());
		
		 String output = hospitalObj.deleteHospitals(hosDel_bean); 
		 
		 return output; 
		 }
}
//package com;
//
//import model.Hospital;
//
//import javax.annotation.security.RolesAllowed;
////For REST Service 
//import javax.ws.rs.*; 
//import javax.ws.rs.core.MediaType; 
//
////For JSON 
//import com.google.gson.*;
//
//import bean.HospitalBean;
//
////For XML 
//import org.jsoup.*; 
//import org.jsoup.parser.*; 
//import org.jsoup.nodes.Document; 
//
//@Path("/Hospital") 
//public class Hospital_Service {  
//	Hospital hospitalObj = new Hospital();
//	
//	//@RolesAllowed({"patient"})
//	@GET  
//	@Path("/")  
//	@Produces(MediaType.TEXT_HTML)  
//	public String readHospitals()  {   
//		return hospitalObj.readHospitals();
//	}
//	
//	//@RolesAllowed({"admin"})
//	@POST
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
//	@Produces(MediaType.TEXT_PLAIN)
////	public String insertHospitals(String HospitalData) {
////		// Convert the input string to a JSON object
////		JsonObject hosjosnObj = new JsonParser().parse(HospitalData).getAsJsonObject();
////	
////		HospitalBean hosBean = new HospitalBean();
////		
////		hosBean.setHospital_Name(hosjosnObj.get("Hospital_Name").getAsString());
////		hosBean.setHospital_Address(hosjosnObj.get("Hospital_Address").getAsString());
////		hosBean.setHospital_City(hosjosnObj.get("Hospital_City").getAsString());
////		hosBean.setHospital_Phone(hosjosnObj.get("Hospital_Phone").getAsString());
////		hosBean.setHospital_Email(hosjosnObj.get("Hospital_Email").getAsString());
////		hosBean.setHospital_Description(hosjosnObj.get("Hospital_Description").getAsString());
////		hosBean.setOpen_Hours(hosjosnObj.get("Open_Hours").getAsInt());
////		
////		// Read the values from the JSON object
////		String output = hospitalObj.insertHospitals(hosBean);
////		return output;
//	public String insertHospitals(@FormParam("Hospital_Name") String hosName,
//			@FormParam("Hospital_Address") String hosAddress,
//			@FormParam("Hospital_City") String hosCity,
//			@FormParam("Hospital_Phone") String phone,
//			@FormParam("Hospital_Email") String email,
//			@FormParam("Hospital_Description") String Desc,
//			@FormParam("Open_Hours") String openhour) {
//			
//		String Output = hospitalObj.insertHospitals(hosName, hosAddress, hosCity, phone, email, Desc, openhour);
//		return Output;
//	}
//	
//	//@RolesAllowed({"admin"})
//	@PUT 
//	@Path("/") 
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String updateHospitals(String HospitalsData) { 
//		//Convert the input string to a JSON object  
//		JsonObject hospitalObject = new JsonParser().parse(HospitalsData).getAsJsonObject(); 
//		
//		 //Read the values from the JSON object  
//		String hosid = hospitalObject.get("Hospital_ID").getAsString();
//		String name = hospitalObject.get("Hospital_Name").getAsString();
//		String address = hospitalObject.get("Hospital_Address").getAsString();
//		String city = hospitalObject.get("Hospital_City").getAsString();
//		String phone = hospitalObject.get("Hospital_Phone").getAsString();
//		String email = hospitalObject.get("Hospital_Email").getAsString();
//		String desc = hospitalObject.get("Hospital_Description").getAsString();
//		String hours = hospitalObject.get("Open_Hours").getAsString();
//		 
//		String output = hospitalObj.updateHospitals(hosid, name, address, city, phone, email, desc, hours);
//		 
//		return output; 
//	}
//
//	//@RolesAllowed({"admin"})
//	@DELETE 
//	@Path("/") 
//	@Consumes(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String deleteHospitals(String HospitalsData) {  
//		// Convert the input string to a JSON object 
//		//JsonObject doc = new JsonParser().parse(HospitalsData).getAsJsonObject();   
//		//HospitalBean hosDel_bean = new HospitalBean();
//		org.jsoup.nodes.Document doc = Jsoup.parse(HospitalsData, "", Parser.xmlParser());
//		//Read the value from the element <Hospital_ID>  
//		//hosDel_bean.setHospital_ID(doc.get("Hospital_ID").getAsInt());
//		
//		String hID = doc.select("Hospital_ID").text();
//		 String output = hospitalObj.deleteHospitals(hID); 
//		 
//		 return output; 
//		 }
//}
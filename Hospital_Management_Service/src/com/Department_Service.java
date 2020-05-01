package com;

import java.util.List;

import javax.annotation.security.RolesAllowed;
//For REST Service 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import bean.DepartmentBean;
import model.Department; 

@Path("/Department") 
public class Department_Service {
	Department departmentObj = new Department();
	
	@RolesAllowed({"admin","doctors","patient"})
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)  
	public String readDepartments()  {
		return departmentObj.readDepartments();
	}
	
	@RolesAllowed({"admin","doctors"})
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDeps(String DepData) {
		// Convert the input string to a JSON object
		JsonObject depjosnObj = new JsonParser().parse(DepData).getAsJsonObject();
	
		DepartmentBean dep_Bean = new DepartmentBean();
		
		dep_Bean.setHospital_ID(depjosnObj.get("Hospital_ID").getAsInt());
		dep_Bean.setDepartment_Name(depjosnObj.get("Department_Name").getAsString());
		dep_Bean.setHead(depjosnObj.get("Head").getAsInt());
		dep_Bean.setStaff_Vacancies(depjosnObj.get("Staff_Vacancies").getAsInt());
		
		// Read the values from the JSON object
		String output = departmentObj.insertDepartments(dep_Bean);
		return output;

	}
	
	@RolesAllowed({"admin","doctors"})
	@PUT 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateDeps(String DepData) { 
		//Convert the input string to a JSON object  
		JsonObject dep_Object = new JsonParser().parse(DepData).getAsJsonObject(); 
		DepartmentBean dep_bean = new DepartmentBean();
		
		 //Read the values from the JSON object  
		dep_bean.setDepartment_ID(dep_Object.get("Department_ID").getAsInt());
		dep_bean.setHospital_ID(dep_Object.get("Hospital_ID").getAsInt());
		dep_bean.setDepartment_Name(dep_Object.get("Department_Name").getAsString());
		dep_bean.setHead(dep_Object.get("Head").getAsInt());
		dep_bean.setStaff_Vacancies(dep_Object.get("Staff_Vacancies").getAsInt());
		
		String output = departmentObj.updateDepartments(dep_bean); 
		 
		return output; 
	}
	
	@RolesAllowed({"admin","doctors"})
	@DELETE 
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDepartments(String DepartmentsData) {  
		// Convert the input string to a JSON object 
		JsonObject doc = new JsonParser().parse(DepartmentsData).getAsJsonObject();
		DepartmentBean depDel_bean = new DepartmentBean();
		
		//Read the value from the elements  
		depDel_bean.setDepartment_ID(doc.get("Department_ID").getAsInt());
		depDel_bean.setHospital_ID(doc.get("Hospital_ID").getAsInt());
		
		String output = departmentObj.deleteDepartments(depDel_bean); 
		 
		return output; 
	}
	
	// View a list of deps by hos ID
	@RolesAllowed({"admin","doctors"})
	@GET
	@Path("/{Hospital_ID}")
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DepartmentBean ShowDepartments(@PathParam("Hospital_ID") int hodid) {
		return departmentObj.ShowDepartments(hodid);
	}
	
}

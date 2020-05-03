package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.DepartmentBean;
import bean.HospitalBean;

public class Department {
	//A common method to connect to the DB 
	private Connection connect() {
		Connection con = null;
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
					 
			//Provide the correct details: DBServer/DBName, username, password 
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/health-system", "root", "");

			//For testing          
			System.out.print("Successfully connected");
					 
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		return con; 
		}
	
		//Read Department Details
	public String readDepartments() {  
		String output = "";  

//		DepartmentBean depReadbean = new DepartmentBean();
//		HospitalBean hospReadbean = new HospitalBean();
		
		try {  
			Connection con = connect();
			if (con == null)  {   
				return "Error while connecting to the database for reading.";  
			} 

			// Prepare the html table to be displayed   
			output = "<table border='1'><tr><th>Hospital ID</th><th>Department Name</th>"
								+ "<th>Head of Department</th><th>Number of Staff Vaconcies</th>"
								+ "<th>Update</th><th>Remove</th></tr>";

		String query1 = "select d.Department_ID,d.Department_Name,h.Hospital_ID,s.DoctorID,d.Staff_Vacancies FROM departments d, doctors s,hospitals h WHERE d.Hospital_ID = h.Hospital_ID AND d.Head = s.DoctorID AND s.Status = 'Accepted'";
		Statement stmt = con.createStatement();   
		ResultSet rs1 = stmt.executeQuery(query1); 
		  
		  // iterate through the rows in the result set  
		  while (rs1.next())   {

			  String Department_ID = Integer.toString(rs1.getInt("Department_ID"));
			  String Hospital_ID = rs1.getString("Hospital_ID");
			  String Department_Name = rs1.getString("Department_Name");
			  String DoctorID = rs1.getString("DoctorID");
			  String Staff_Vacancies = rs1.getString("Staff_Vacancies");

		   // Add into the html table    

		  output += "<tr><td><input id='hidDepartmentIDUpdate' name='hidDepartmentIDUpdate' type='hidden' value='" + Department_ID + "'>" + Hospital_ID + "</td>";
		  output += "<td>" + Department_Name + "</td>";    
		  output += "<td>" + DoctorID + "</td>"; 
		  output += "<td>" + Staff_Vacancies + "</td>"; 
		  
		  // buttons     
		  output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
		  		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-departmentid='"+ Department_ID +"'>"+"</td></tr>";

		
		  } 

		  con.close(); 

		  // Complete the html table   
		  output += "</table>"; 
		}
		catch (Exception e) {  
			output = "Error while reading the Department data.";  
			System.err.println(e.getMessage()); 
		}

		return output;
	}
	
	//Insert departments
	public String insertDepartments(String hospId, String depName, String depHead, String staffVacan) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement   
			String query = " insert into departments (`Department_ID`,`Hospital_ID`,`Department_Name`,`Head`,`Staff_Vacancies`)"+" values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values 
//			preparedStmt.setInt(1, depBean.getHospital_ID());
//			preparedStmt.setString(2, depBean.getDepartment_Name());    
//			preparedStmt.setInt(3, depBean.getHead());
//			preparedStmt.setInt(4, depBean.getStaff_Vacancies());
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(hospId));
			preparedStmt.setString(3, depName);
			preparedStmt.setInt(4, Integer.parseInt(depHead));
			preparedStmt.setInt(5, Integer.parseInt(staffVacan));

			//execute the statement   
			preparedStmt.execute();   
			con.close(); 

			//Create JSON Object to show successful msg.
			String newDepartments = readDepartments();
			output = "{\"status\":\"success\", \"data\": \"" + newDepartments + "\"}";
		}
		catch (Exception e) {   
			//Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting new Department to the Hospital.\"}";  
			System.err.println(e.getMessage());  
		} 

		 return output; 
	}

	public String updateDepartments(String depid, String hospid, String depname, String head, String vacancies)  {   
		String output = ""; 
	 
	  try   {   
		  Connection con = connect();
	 
		  if (con == null)    {
			  return "Error while connecting to the database for updating."; 
		  } 
	 
	   // create a prepared statement    
	   String query = "UPDATE departments SET Hospital_ID=?,Department_Name=?,Head=?,Staff_Vacancies=?      "
	   		+ "			WHERE Department_ID=?"; 
	 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
//	   preparedStmt.setInt(1, depUpdateBean.getHospital_ID());
//	   preparedStmt.setString(2, depUpdateBean.getDepartment_Name());    
//	   preparedStmt.setInt(3, depUpdateBean.getHead());
//	   preparedStmt.setInt(4, depUpdateBean.getStaff_Vacancies());
//	   preparedStmt.setInt(5, depUpdateBean.getDepartment_ID());
	   preparedStmt.setInt(1, Integer.parseInt(hospid));    
	   preparedStmt.setString(2, depname);    
	   preparedStmt.setInt(3, Integer.parseInt(head));
	   preparedStmt.setInt(4, Integer.parseInt(vacancies));
	   preparedStmt.setInt(5, Integer.parseInt(depid));
	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	 //create JSON object to show successful msg
	   String newDepartments = readDepartments();
	   output = "{\"status\":\"success\", \"data\": \"" + newDepartments + "\"}";
	   
	   }   catch (Exception e)   {    
		   output = "{\"status\":\"error\", \"data\": \"Error while Updating Department Details.\"}";    
		   System.err.println(e.getMessage());   
	   } 
	 
	  return output;  
	  }
	
	public String deleteDepartments(String Department_ID) {  
		String output = ""; 
	 
	 try  {   
		 Connection con = connect();
	 
	  if (con == null)   {    
		  return "Error while connecting to the database for deleting.";   
	  } 
	 
	  // create a prepared statement   
	  String query = "DELETE FROM departments WHERE Department_ID=?";
	  
	  PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	  // binding values   
	  preparedStmt.setInt(1, Integer.parseInt(Department_ID));
	  // execute the statement   
	  preparedStmt.execute();   
	  con.close(); 
	 
	//create JSON Object
	  String newDepartments = readDepartments();
	  output = "{\"status\":\"success\", \"data\": \"" + newDepartments + "\"}";
	  
	  }  catch (Exception e)  {   
		//Create JSON object 
		  output = "{\"status\":\"error\", \"data\": \"Error while Deleting Department from the Hospital.\"}";
		  System.err.println(e.getMessage());  
		  
	 } 
	 
	 return output; 
	 }
	
	//Search method
	public List<DepartmentBean> viewDeps() {
		
		return	viewDeps(0);

	}
	
	//show the Hospital by ID
	public DepartmentBean ShowDepartments(int hosid) {
	List<DepartmentBean> list = viewDeps(hosid);
		if(!list.isEmpty()) {
			return	list.get(0);
		}
		return null;
	}
	
	//view method
	public List<DepartmentBean> viewDeps(int hosid) {
			List <DepartmentBean> depList = new ArrayList<>();
			
		try 
		{
			Connection con = connect();
			if (con == null) {
				
				System.out.println("Error While reading from database");
				return depList;
			}

			String query;
			
			if(hosid == 0) {
				query = "select * from departments";
			}
			else {
				query = "SELECT * FROM departments where Hospital_ID= "+hosid;	
			}

			Statement Stmt = con.createStatement();
			ResultSet result = Stmt.executeQuery(query);

			while (result.next()) {
				DepartmentBean deps = new DepartmentBean(
						result.getInt("Hospital_ID"),
						result.getInt("Department_ID"),
						result.getString("Department_Name"),
						result.getInt("Head"),
						result.getInt("Staff_Vacancies")
				);
				depList.add(deps);
			}
			
			con.close();
		
		}catch (Exception e) {
			System.out.println("Error While Reading");
			System.err.println(e.getMessage());
		}
		return depList;
	}
	
//	public List<DepartmentBean> ShowDepartments_By_hId(int hid) {
//		List<DepartmentBean> depsBeanlist = new ArrayList<>();
//		for (DepartmentBean i : viewDeps()) {
//			if (hid.equals(i.getHospital_ID())) {				
//				depsBeanlist.add(i );
//			}
//		}
//		return depsBeanlist;
//	}

}
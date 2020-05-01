package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import bean.HospitalBean;

public class Hospital {
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
	
	public String readHospitals() {  
		String output = "";  
		
		HospitalBean hosReadbean = new HospitalBean();

		try {  
			Connection con = connect();  
			if (con == null)  {   
				return "Error while connecting to the database for reading.";  
			} 

			// Prepare the html table to be displayed   
			output = "<table border=\"1\"><tr><th>Hospital ID</th>"    +""
					+ "<th>Hospital Name</th>"    + ""
					+ "<th>Hospital Address</th><th>Hospital City</th>"    + ""
					+ "<th>Hospital Phone</th><th>Hospital Email</th>"    + ""
					+ "<th>Hospital Description</th><th>Open Hours</th>";


			  String query = "select * from hospitals";   
			  Statement stmt = con.createStatement();   
			  ResultSet rs = stmt.executeQuery(query); 

			  // iterate through the rows in the result set   
			  while (rs.next())   {  
				  hosReadbean.setHospital_ID(rs.getInt("Hospital_ID"));
				  hosReadbean.setHospital_Name(rs.getString("Hospital_Name"));
				  hosReadbean.setHospital_Address(rs.getString("Hospital_Address"));
				  hosReadbean.setHospital_City(rs.getString("Hospital_City"));
				  hosReadbean.setHospital_Phone(rs.getString("Hospital_Phone"));
				  hosReadbean.setHospital_Email(rs.getString("Hospital_Email"));
				  hosReadbean.setHospital_Description(rs.getString("Hospital_Description"));
				  hosReadbean.setOpen_Hours(rs.getInt("Open_Hours"));

			   // Add into the html table    
			  output += "<tr><td>" + hosReadbean.getHospital_ID() + "</td>";    
			  output += "<td>" + hosReadbean.getHospital_Name() + "</td>";
			  output += "<td>" + hosReadbean.getHospital_Address() + "</td>";    
			  output += "<td>" + hosReadbean.getHospital_City() + "</td>"; 
			  output += "<td>" + hosReadbean.getHospital_Phone() + "</td>";    
			  output += "<td>" + hosReadbean.getHospital_Email() + "</td>";
			  output += "<td>" + hosReadbean.getHospital_Description() + "</td>";		  
			  output += "<td>" + hosReadbean.getOpen_Hours() + "</td>"; 
			  } 

			  con.close(); 

			  // Complete the html table   
			  output += "</table>"; 
			}
			catch (Exception e) {  
				output = "Error while reading the Hospital data.";  
				System.err.println(e.getMessage()); 
			}

			return output;
		}
	
	//Insert Hospitals
	public String insertHospitals(HospitalBean hos_bean) {
		String output = "";

		try {
			Connection con = connect();  

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement   
			String query = " insert into hospitals (`Hospital_Name`,`Hospital_Address`,`Hospital_City`,`Hospital_Phone`,`Hospital_Email`,`Hospital_Description`,`Open_Hours`)"+" values (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values 
			preparedStmt.setString(1, hos_bean.getHospital_Name());   
			preparedStmt.setString(2, hos_bean.getHospital_Address());    
			preparedStmt.setString(3, hos_bean.getHospital_City());
			preparedStmt.setString(4, hos_bean.getHospital_Phone());
			preparedStmt.setString(5, hos_bean.getHospital_Email());
			preparedStmt.setString(6, hos_bean.getHospital_Description());
			preparedStmt.setInt(7, hos_bean.getOpen_Hours());  

			//execute the statement   
			preparedStmt.execute();   
			con.close(); 

			output = "Inserted successfully";
		}
		catch (Exception e) {   
			output = "Error while inserting the Hospitals.";   
			System.err.println(e.getMessage());  
		} 

		 return output; 
	}
	
	//Update hospitals
	public String updateHospitals(HospitalBean hosUpdateBean)  {   
		String output = ""; 
	 
	  try   {   
		  Connection con = connect();
	 
		  if (con == null)    {
			  return "Error while connecting to the database for updating."; 
		  } 
	 
	   // create a prepared statement    
		   String query = "UPDATE hospitals SET Hospital_Name=?,Hospital_Address=?,Hospital_City=?,Hospital_Phone=?,Hospital_Email=?,Hospital_Description=?,Open_Hours=?      "
			   		+ "			WHERE Hospital_ID=?"; 
			 
	   PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	   // binding values    
	   preparedStmt.setString(1, hosUpdateBean.getHospital_Name());    
	   preparedStmt.setString(2, hosUpdateBean.getHospital_Address());    
	   preparedStmt.setString(3, hosUpdateBean.getHospital_City());
	   preparedStmt.setString(4, hosUpdateBean.getHospital_Phone());
	   preparedStmt.setString(5, hosUpdateBean.getHospital_Email());
	   preparedStmt.setString(6, hosUpdateBean.getHospital_Description());
	   preparedStmt.setInt(7, hosUpdateBean.getOpen_Hours());
	   preparedStmt.setInt(8, hosUpdateBean.getHospital_ID());
	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	   output = "Updated successfully "+hosUpdateBean.getHospital_Name()+" Details";   
	   }   catch (Exception e)   {    
		   output = "Error while updating the Hospital.";    
		   System.err.println(e.getMessage());   
	   } 
	 
	  return output;  
	  }
	
	public String deleteHospitals(HospitalBean hosDeleteBean) {  
		String output = ""; 
	 
	 try  {   
		 Connection con = connect();
	 
	  if (con == null)   {    
		  return "Error while connecting to the database for deleting.";   
	  } 
	 
	  // create a prepared statement   
	  String query = "DELETE FROM hospitals WHERE Hospital_ID=?"; 
	 
	  PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	  // binding values   
	  preparedStmt.setInt(1, hosDeleteBean.getHospital_ID());       
	  // execute the statement   
	  preparedStmt.execute();   
	  con.close(); 
	 
	  output = "Deleted successfully";  
	  }  catch (Exception e)  {   
		  output = "Error while deleting the hospital.";   
		  System.err.println(e.getMessage());  
		  
	 } 
	 
	 return output; 
	 }
}
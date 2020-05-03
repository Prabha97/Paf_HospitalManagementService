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
			output = "<table border='1'><tr><th>Hospital Name</th>"    + ""
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
				  //output += "<tr><td>" + hosReadbean.getHospital_ID() + "</td>";
				  output += "<tr><td><input id=\"hidHospitalIDUpdate\" name=\"hidHospitalIDUpdate\"     "
				  		+ "type=\"hidden\" value=\"" + hosReadbean.getHospital_ID() + "\">"     
						  	+ hosReadbean.getHospital_Name() + "</td>"; 
				  //output += "<td>" + hosReadbean.getHospital_Name() + "</td>";
				  output += "<td>" + hosReadbean.getHospital_Address() + "</td>";    
				  output += "<td>" + hosReadbean.getHospital_City() + "</td>"; 
				  output += "<td>" + hosReadbean.getHospital_Phone() + "</td>";    
				  output += "<td>" + hosReadbean.getHospital_Email() + "</td>";
				  output += "<td>" + hosReadbean.getHospital_Description() + "</td>";		  
				  output += "<td>" + hosReadbean.getOpen_Hours() + "</td>"; 
				  
				  

				// buttons     
				  output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						  + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-hospitalid='"       
						  + hosReadbean.getHospital_ID() + "'>" + "</td></tr>";  
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
	public String insertHospitals(String hosName, String hosAddress, String hosCity, String phone, String email, String desc, String openhour) {
		String output = "";

		try {
			Connection con = connect();  

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement   
			String query = " insert into hospitals (`Hospital_ID`,`Hospital_Name`,`Hospital_Address`,`Hospital_City`,`Hospital_Phone`,`Hospital_Email`,`Hospital_Description`,`Open_Hours`)"+" values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values 
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hosName);
			preparedStmt.setString(3, hosAddress);
			preparedStmt.setString(4, hosCity);
			preparedStmt.setString(5, phone);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, desc);
			preparedStmt.setInt(8, Integer.parseInt(openhour));

			//execute the statement   
			preparedStmt.execute();   
			con.close(); 

			//Create JSON Object to show successful msg.
			String newHospital = readHospitals();
			output = "{\"status\":\"success\", \"data\": \"" + newHospital + "\"}";
		}
		catch (Exception e) {  
			//Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting new Hospital.\"}";   
			System.err.println(e.getMessage());  
		} 

		 return output; 
	}
	
	//Update hospitals
	public String updateHospitals(String hosid, String name, String address, String city, String phone, String email,String desc, String hours)  {   
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
	   preparedStmt.setString(1, name);    
	   preparedStmt.setString(2, address);    
	   preparedStmt.setString(3, city);
	   preparedStmt.setString(4, phone);
	   preparedStmt.setString(5, email);
	   preparedStmt.setString(6, desc);
	   preparedStmt.setInt(7, Integer.parseInt(hours));
	   preparedStmt.setInt(8, Integer.parseInt(hosid));
	   
	 
	   // execute the statement    
	   preparedStmt.execute();    
	   con.close(); 
	 
	   //create JSON object to show successful msg
	   String UpdatedHospital = readHospitals();
	   output = "{\"status\":\"success\", \"data\": \"" + UpdatedHospital + "\"}";
	   }   catch (Exception e)   {    
		   output = "{\"status\":\"error\", \"data\": \"Error while Updating Hospital's Details.\"}";      
		   System.err.println(e.getMessage());   
	   } 
	 
	  return output;  
	  }
	
	public String deleteHospitals(String hID) {  
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
	  preparedStmt.setInt(1, Integer.parseInt(hID));       
	  // execute the statement   
	  preparedStmt.execute();   
	  con.close(); 
	 
	  //create JSON Object
	  String delHospital = readHospitals();
	  output = "{\"status\":\"success\", \"data\": \"" + delHospital + "\"}";
	  }  catch (Exception e)  {  
		  //Create JSON object 
		  output = "{\"status\":\"error\", \"data\": \"Error while Deleting Hospital.\"}";
		  System.err.println(e.getMessage());  
		  
	 } 
	 
	 return output; 
	 }
}
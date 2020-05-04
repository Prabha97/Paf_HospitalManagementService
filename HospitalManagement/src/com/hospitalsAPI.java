package com;

import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hospital;

/**
 * Servlet implementation class hospitalsAPI
 */
//@WebServlet("/hospitalsAPI")
@WebServlet(name="hospitalsAPI", urlPatterns={"/hospitalsAPI"})
public class hospitalsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Hospital hospObj = new Hospital();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hospitalsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("Inside doGet method");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doPost method");
		
		String result = hospObj.insertHospitals(request.getParameter("hospitalName"), request.getParameter("hospitalAddress"), 
				request.getParameter("hospitalCity"), request.getParameter("phoneNum"), 
				request.getParameter("hospitalEmail"), request.getParameter("hospitalDesc"), 
				request.getParameter("openHours"));
		
		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();  
		try  {   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";   
			scanner.close(); 
		 
		  String[] params = queryString.split("&");   
		  for (String param : params)   {
			  String[] p = param.split("=");    
			  map.put(p[0], p[1]);   
		  }  
		  
		}catch (Exception e)  {  
			
		} 
		return map;
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//use Map object
		Map<String, String> param = getParasMap(request);
		
		String result = hospObj.updateHospitals(param.get("hidHospitalIDSave").toString(),
				param.get("hospitalName").toString().replace('+', ' '),
				param.get("hospitalAddress").toString().replace('+', ' '),
				param.get("hospitalCity").toString().replace('+', ' '), 
				param.get("phoneNum").toString(),
				param.get("hospitalEmail").toString().replace("%40", "@"),
				param.get("hospitalDesc").toString().replace('+', ' '),
				param.get("openHours").toString());
		
		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> param = getParasMap(request);
		
		String result = hospObj.deleteHospitals(param.get("Hospital_ID").toString());
		
		response.getWriter().write(result);

	}

}

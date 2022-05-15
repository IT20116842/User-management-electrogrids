package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

public class userManage {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// this sample 1

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electro_grid?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	//Insert User
	public String insertUser(String userName, String userAddress, String userNIC, String userEmail, String userPNO) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user(`uID`,`userName`,`userAddress`,`userNIC`,`userEmail`,`userPNO`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, userAddress);
			preparedStmt.setString(4, userNIC);
			preparedStmt.setString(5, userEmail);
			preparedStmt.setString(6, userPNO);
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newUser = readUser(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";  

		} catch (Exception e) {
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the User. Try again\"}";  
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Read User
	public String readUser() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User Name</th><th>User Address</th><th>User NIC</th><th>User Email</th><th>User Phone Number</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from user";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String uID = Integer.toString(rs.getInt("uID"));
				String userName = rs.getString("userName");
				String userAddress = rs.getString("userAddress");
				String userNIC = rs.getString("userNIC");
				String userEmail = rs.getString("userEmail");
				String userPNO = rs.getString("userPNO");

				// Add into the html table
				output += "<tr><td><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='" + uID + "'>" + userName + "</td>";
				output += "<td>" + userAddress + "</td>";
				output += "<td>" + userNIC + "</td>";
				output += "<td>" + userEmail + "</td>";
				output += "<td>" + userPNO + "</td>";
			
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='hidItemIDDelete' type='button' value='Remove' class='btnRemove btn btn-danger' data-uid='" + uID + "'>" + "</td></tr>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the User.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//Update User
	public String updateUser(String uID, String userName, String userAddress, String userNIC, String userEmail, String userPNO) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE user SET userName=?,userAddress=?,userNIC=?,userEmail=?,userPNO=?" + "WHERE uID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, userAddress);
			preparedStmt.setString(3, userNIC);
			preparedStmt.setString(4, userEmail);
			preparedStmt.setString(5, userPNO);
			preparedStmt.setInt(6, Integer.parseInt(uID));

			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" +  newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the User.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 

	// Delete User
	public String deleteUser(String uID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from user where uID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(uID));

			// execute the statement
			preparedStmt.execute();
			con.close(); 
	 
			String  newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" +  newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the User.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}

}


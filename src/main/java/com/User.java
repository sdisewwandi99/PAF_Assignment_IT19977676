package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	//*************************read user****************************************
	public String readUsers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table><tr><th style=width:150px; >NIC</th>"
					+ "<th style=width:150px; >Name</th>"
					+ "<th style=width:150px; >Address</th>"
					+ "<th style=width:150px; >Contact</th>"
					+ "<th style=width:10px; >Update</th>"
					+ "<th style=width:10px; >Remove</th></tr>";

			String query = "select * from person";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String userid = Integer.toString(rs.getInt("userid"));
				String nic = rs.getString("nic");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String contact = rs.getString("contact");
// Add into the html table
				output += "<tr><td><input id='hidUserIDUpdate'name='hidUserIDUpdate'type='hidden' value='" + userid
						+ "'>" + nic + "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + contact + "</td>";
// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'>"
						+ "</td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='"+userid+"'></td></tr>";
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading users.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//***********************************insert user****************************************
public String insertUser(String nic, String name, String address, String contact)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for inserting.";
}
// create a prepared statement
String query = " insert into person(`userid`,`nic`,`name`,`address`,`contact`) values (?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, nic);
preparedStmt.setString(3, name);
preparedStmt.setString(4, address);
preparedStmt.setString(5, contact);
// execute the statement
preparedStmt.execute();
con.close();
String newUsers = readUsers();
output = "{\"status\":\"success\", \"data\": \"" +
newUsers + "\"}";
}
catch (Exception e)
{
output = "status:error ,data:Error while inserting user.";
System.err.println(e.getMessage());
}
return output;
}

//***********************************update user****************************************
public String updateUser(String userid,String nic, String name, String address, String contact)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for updating.";
}
// create a prepared statement
String query = "UPDATE person SET nic=?,name=?,address=?,contact=? WHERE userid=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, nic);
preparedStmt.setString(2, name);
preparedStmt.setString(3, address);
preparedStmt.setString(4, contact);
preparedStmt.setInt(5, Integer.parseInt(userid));
// execute the statement
preparedStmt.execute();
con.close();
String newUsers = readUsers();
output = "{\"status\":\"success\", \"data\": \"" +
newUsers + "\"}";
}
catch (Exception e)
{
output = "status:error data Error while updating the user.";
System.err.println(e.getMessage());
}
return output;
}



//***********************************delete employers****************************************

public String deleteUser(String userid)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for deleting.";
}
// create a prepared statement
String query = "delete from person where userid=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(userid));
// execute the statement
preparedStmt.execute();
con.close();
String newUsers = readUsers();
output = "{\"status\":\"success\", \"data\": \"" +
newUsers + "\"}";
}
catch (Exception e)
{
output = "status:error data :Error while deleting user.";
System.err.println(e.getMessage());
}
return output;
}
}
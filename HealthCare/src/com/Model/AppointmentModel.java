package com.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppointmentModel {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/health_care", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertAppointment(String date, String time)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for inserting."; }
	// create a prepared statement
	String query = " insert into appointment(`appointmentID`,`appointmentDate`,`appointmentTime`)"+ " values (?, ?, ?)";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, 0);
	preparedStmt.setString(2, date);
	preparedStmt.setString(3, time);
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
			}
			catch (Exception e)
			{
			output = "Error while inserting the appointment.";
			System.err.println(e.getMessage());
			}
			return output;
			}

	public String readAppointment()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{return "Error while connecting to the database for reading."; }
	// Prepare the html table to be displayed
	output = "<table border=\"1\"><tr><th>Appointment Date</th><th>Appointment Time</th><th>Update</th><th>Remove</th></tr>";
	String query = "select * from appointment";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
	String appointmentID = Integer.toString(rs.getInt("appointmentID"));
	String appointmentDate = rs.getString("appointmentDate");
	String appointmentTime = rs.getString("appointmentTime");
	
	// Add into the html table
	output += "<tr><td>" + appointmentDate + "</td>";
	output += "<td>" + appointmentTime + "</td>";
	
	// buttons
	output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"appointment.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"+ "<input name=\"appointmentID\" type=\"hidden\" value=\"" + appointmentID+ "\">" + "</form></td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the items.";
	System.err.println(e.getMessage());
	}
	return output;
	}

	public String updateAppointment(String ID, String date, String time)
			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE appointment SET appointmentDate=?,appointmentTime=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, date);
			preparedStmt.setString(2, time);
			
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			}
			catch (Exception e)
			{
			output = "Error while updating the appointment.";
			System.err.println(e.getMessage());
			}
			return output;
			}

	public String deleteItem(String appointmentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from items where appointmentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(appointmentID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the appointment";
			System.err.println(e.getMessage());
		}
		return output;
	}

}	
	

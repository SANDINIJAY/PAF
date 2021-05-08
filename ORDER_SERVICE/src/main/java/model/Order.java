package model;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Order {
	//A common method to connect to the DB
		private Connection connect()
		{
		Connection con = null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		//Provide the correct details: DBServer/DBName, username, password
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/order", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
		}
		public String insertOrder(String buyerId, String buyerName, String paymentdetails)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for inserting."; }
		// create a prepared statement
		String query = " INSERT INTO `order`(`orderId`, `buyerId`, `buyerName`, `paymentdetails`)"
		+ " values (?, ?, ?, ?)";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, buyerId);
		preparedStmt.setString(3, buyerName);
		preparedStmt.setString(4, paymentdetails);
		
		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "Inserted successfully";
		}
		catch (Exception e)
		{
		output = "Error while inserting the order.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String readOrder()
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{return "Error while connecting to the database for reading."; }
		// Prepare the html table to be displayed
		output = "<table border='1'><tr><th>buyer ID</th>" +
		"<th>buyer name</th>" +
		"<th>payment details</th>";
		
		String query = "SELECT * FROM order";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		while (rs.next())
		{
		String orderId = Integer.toString(rs.getInt("orderId"));
		String buyerId = rs.getString("buyerId");
		String buyerName = rs.getString("buyerName");
		String paymentdetails = rs.getString("paymentdetails");
		
		// Add into the html table
		output += "<tr><td>" + buyerId + "</td>";
		output += "<td>" + buyerName + "</td>";
		output += "<td>" + paymentdetails + "</td>";
		
		}
		con.close();
		// Complete the html table
		output += "</table>";
		}
		catch (Exception e)
		{
		output = "Error while reading the order.";
		System.err.println(e.getMessage());
		}
		return output;
		}
		public String updateOrder(String orderId,String buyerId, String buyerName, String paymentdetails)
		{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE order SET buyerId=?,buyerName=?,paymentdetails=? WHERE orderId=? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, buyerId);
			preparedStmt.setString(2, buyerName);
			preparedStmt.setString(3, paymentdetails);
			
			preparedStmt.setInt(5, Integer.parseInt(orderId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
			}
			catch (Exception e)
			{
			output = "Error while updating the order.";
			System.err.println(e.getMessage());
			}
			return output;
			}
		
		
			public String deleteOrder(String orderId)
			{
			String output = "";
			try
			{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from order where orderId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(orderId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
			}
			catch (Exception e)
			{
			output = "Error while deleting the order.";
			System.err.println(e.getMessage());
			}
			return output;
			}

}
package model;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

 

public class User {
    //A common method to connect to the DB
        private Connection connect()
        {
        Connection con = null;
        try
        {
        Class.forName("com.mysql.jdbc.Driver");
        //Provide the correct details: DBServer/DBName, username, password
        con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "");
        }
        catch (Exception e)
        {e.printStackTrace();}
        return con;
        }
        public String insertUser(String userID, String userName, String userType, String userAddress)
        {
        String output = "";
        try
        {
        Connection con = connect();
        if (con == null)
        {return "Error while connecting to the database for inserting."; }
        // create a prepared statement
        String query = " INSERT INTO `user`(`userCode`, `userID`, `userName`, `userType`, `userAddress`)"
        + " values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = con.prepareStatement(query);
        // binding values
        preparedStmt.setInt(1, 0);
        preparedStmt.setString(2, userID);
        preparedStmt.setString(3, userName);
        preparedStmt.setString(4, userType);
        preparedStmt.setString(5, userAddress);
        // execute the statement
        preparedStmt.execute();
        con.close();
        output = "Inserted successfully";
        }
        catch (Exception e)
        {
        output = "Error while inserting the user.";
        System.err.println(e.getMessage());
        }
        return output;
        }
        public String readUser()
        {
        String output = "";
        try
        {
        Connection con = connect();
        if (con == null)
        {return "Error while connecting to the database for reading."; }
        // Prepare the html table to be displayed
        output = "<table border='1'><tr><th>User ID</th>" +
        "<th>User Name</th>" +
        "<th>User Type</th>"+
        "<th>User Address</th>";
        
        String query = "SELECT * FROM user";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        // iterate through the rows in the result set
        while (rs.next())
        {
        String userCode = Integer.toString(rs.getInt("userCode"));
        String userID = rs.getString("userID");
        String userName = rs.getString("userName");
        String userType = rs.getString("userType");
        String userAddress = rs.getString("userAddress");
        // Add into the html table
        output += "<tr><td>" + userID + "</td>";
        output += "<td>" + userName + "</td>";
        output += "<td>" + userType + "</td>";
        output += "<td>" + userAddress + "</td>";
        
        }
        con.close();
        // Complete the html table
        output += "</table>";
        }
        catch (Exception e)
        {
        output = "Error while reading the payment.";
        System.err.println(e.getMessage());
        }
        return output;
        }
        public String updateUser(String userCode,String userID, String userName, String userType, String userAddress)
        {
            String output = "";
            try
            {
            Connection con = connect();
            if (con == null)
            {return "Error while connecting to the database for updating."; }
            // create a prepared statement
            String query = "UPDATE user SET userID=?,userName=?,userType=?,userAddress=? WHERE userCode=? ";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            // binding values
            
            preparedStmt.setString(1, userID);
            preparedStmt.setString(2, userName);
            preparedStmt.setString(3, userType);
            preparedStmt.setString(4, userAddress);
            preparedStmt.setInt(5, Integer.parseInt(userCode));
            // execute the statement
            preparedStmt.execute();
            con.close();
            output = "Updated successfully";
            }
            catch (Exception e)
            {
            output = "Error while updating the user.";
            System.err.println(e.getMessage());
            }
            return output;
            }
        
        
            public String deleteUser(String userCode)
            {
            String output = "";
            try
            {
            Connection con = connect();
            if (con == null)
            {return "Error while connecting to the database for deleting."; }
            // create a prepared statement
            String query = "delete from user where userCode=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            // binding values
            preparedStmt.setInt(1, Integer.parseInt(userCode));
            // execute the statement
            preparedStmt.execute();
            con.close();
            output = "Deleted successfully";
            }
            catch (Exception e)
            {
            output = "Error while deleting the user.";
            System.err.println(e.getMessage());
            }
            return output;
            }

 

}

package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddToSQLDatabase 
{
	/* Given a new username and password by the new user, check that the username is not taken. If the username
	 * is not taken, add the new user into the database, with level 1 and 0 wins and losses. 
	 * If the username is taken, let the new user know that the username is taken, and don't update the
	 * database */
	
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		String userToAdd = "";
		String passwordToAdd = "";
		Scanner input = new Scanner(System.in);
		boolean validName = false;
		boolean validPass = false;
		
		// Getting valid username and password from user
		while (validName == false)
		{
			System.out.println("Enter Username to add: ");
			userToAdd = input.nextLine().trim();
			if (userToAdd.contains(" "))
			{
				System.out.println("Invalid Username! No spaces.");
				continue;
			}
			else
			{
				validName = true;
			}
		}
		while (validPass == false)
		{
			
			System.out.println("Enter Password to add: ");
			passwordToAdd = input.nextLine().trim();
			if (passwordToAdd.contains(" "))
			{
				System.out.println("Invalid Password! No spaces.");
			}
			else
			{
				validPass = true;
			}
		}
		
		try
		{
			// Reflection
			// Telling JDK that when you compile code, this class will be there at runtime
			Class.forName("com.mysql.jdbc.Driver");
			// Get connection to the SQL Database. Use SSL=false to tell SQL to not give you the warning that you aren't using SSL
			// SQLException might get thrown here if there is an error
			
			String yourPassword = "spideyspider"; // Write your password here to access the database
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ProjectUserDatabase?user=root&password=" + yourPassword + "&useSSL=false");
			// How to access the database in other users' computers?
			
			// Check if username already exists
			
			ps = conn.prepareStatement(" INSERT INTO ProjectUserTable (username, "
										+ "userPassword, userLevel, userWins, userLosses) " + " VALUES "
										+ "(?, ?, 1, 0, 0);");
			ps.setString(1, userToAdd);
			ps.setString(2, passwordToAdd);
			
			ps.executeUpdate();
			System.out.println("User successfully added!");
		}
		catch (SQLException io)
		{
			System.out.println("Username taken! Please choose another.");
			System.out.println("sqle: " + io.getMessage());
		}
		catch (ClassNotFoundException io)
		{
			System.out.println("cnfe: " + io.getMessage());
		}
		finally
		{
			try
			{
				if (ps != null)
				{
					ps.close();
				}
				if (rs != null)
				{
					rs.close();
				}
				if (st != null)
				{
					st.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (SQLException io)
			{
				System.out.println("Error in closing stream: " + io.getMessage());
			}
		}
		input.close();
	}
}

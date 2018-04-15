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
	private static final String SQL_SERIALIZE_OBJECT_PLAYER = "INSERT INTO PlayerObject(username, serialized_object_player) VALUES (?, ?)";
	private static final String SQL_SERIALIZE_OBJECT_DECKS = "INSERT INTO DecksObject(username, serialized_object_decks) VALUES (? , ?)";
	private static final String SQL_DESERIALIZE_OBJECT_PLAYER = "SELECT serialized_object_player FROM PlayerObject WHERE serialized_id_player = ?";
	private static final String SQL_DESERIALIZE_OBJECT_DECKS = "SELECT serialized_object_decks FROM DecksObject WHERE serialized_id_decks = ?";

	SerializeToDatabase STD = new SerializeToDatabase();
	/* Given a new username and password by the new user, check that the username is not taken. If the username
	 * is not taken, add the new user into the database, with level 1 and 0 wins and losses. 
	 * If the username is taken, let the new user know that the username is taken, and don't update the
	 * database */
	public void refreshToDatabase(String username, String password, int wins, int losses, int level, Player player, Decks decks) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String yourPassword = "Equyi86V"; // Write your password here to access the database
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ProjectUserDatabase?user=root&password=" + yourPassword + "&useSSL=false");
			// How to access the database in other users' computers?
			ps = conn.prepareStatement("DELETE FROM ProjectUserTable " + 
					" WHERE username=?;");
			ps.setString(1, username);
			ps.executeUpdate();
			
			ps = conn.prepareStatement("DELETE FROM PlayerObject " + 
					" WHERE username=?;");
			ps.setString(1, username);
			ps.executeUpdate();
			
			ps = conn.prepareStatement("DELETE FROM DecksObject " + 
					" WHERE username=?;");
			ps.setString(1, username);
			ps.executeUpdate();
			
			ps = conn.prepareStatement(" INSERT INTO ProjectUserTable (username, "
					+ "userPassword, userLevel, userWins, userLosses) " + " VALUES "
					+ "(?, ?, ?, ?, ?);");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, level);
			ps.setInt(4, wins);
			ps.setInt(5, losses);
			ps.executeUpdate();
			
			STD.serializeJavaObjectToDB(conn, player, username, SQL_SERIALIZE_OBJECT_PLAYER);
			STD.serializeJavaObjectToDB(conn, decks, username, SQL_SERIALIZE_OBJECT_DECKS);
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
	}
	
 	public User addToDatabase(String userToAdd, String passwordToAdd) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		boolean validName = false;
		boolean validPass = false;
		User user = null;
		// Getting valid username and password from user
		while (validName == false)
		{
			if (userToAdd.contains(" "))
			{
				System.out.println("Invalid Username! No spaces.");
			}
			else
			{
				validName = true;
			}
		}
		while (validPass == false)
		{
			
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
			
			String yourPassword = "Equyi86V"; // Write your password here to access the database
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost/ProjectUserDatabase?user=root&password=" + yourPassword + "&useSSL=false");
			// How to access the database in other users' computers?
			
			// Check if username already exists
			
			ps = conn.prepareStatement(" INSERT INTO ProjectUserTable (username, "
										+ "userPassword, userLevel, userWins, userLosses) " + " VALUES "
										+ "(?, ?, 1, 0, 0);");
			ps.setString(1, userToAdd);
			ps.setString(2, passwordToAdd);
			
			ps.executeUpdate();
			Player player = new Player();
			STD.serializeJavaObjectToDB(conn, player, userToAdd, SQL_SERIALIZE_OBJECT_PLAYER);
			Decks decks = new Decks();
			STD.serializeJavaObjectToDB(conn, decks, userToAdd, SQL_SERIALIZE_OBJECT_DECKS);
			System.out.println("User successfully added!");
			user = new User(decks, userToAdd, passwordToAdd, player, 0, 0, 1);
			return user;
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
		return user;
	}
	public static void main(String[] args)
	{
		AddToSQLDatabase ATSD = new AddToSQLDatabase();
		ATSD.addToDatabase("fd", "fd");
	}
}

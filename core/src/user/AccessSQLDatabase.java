package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mygdx.game.LoginScreen;

public class AccessSQLDatabase
{
	// REMEMBER TO CHANGE YOUR PASSWORD TO AVOID EXCEPTIONS!!!
	private static final String SQL_SERIALIZE_OBJECT_PLAYER = "INSERT INTO PlayerObject(username, serialized_object_player) VALUES (?, ?)";
	private static final String SQL_SERIALIZE_OBJECT_DECKS = "INSERT INTO DecksObject(username, serialized_object_decks) VALUES (? , ?)";
	private static final String SQL_DESERIALIZE_OBJECT_PLAYER = "SELECT serialized_object_player FROM PlayerObject WHERE serialized_id_player = ?";
	private static final String SQL_DESERIALIZE_OBJECT_DECKS = "SELECT serialized_object_decks FROM DecksObject WHERE serialized_id_decks = ?";

	LoginScreen lscreen;
	
	// Constructors
	public AccessSQLDatabase()
	{
		
	}
	
	public AccessSQLDatabase(LoginScreen ls)
	{
		lscreen = ls;
	}
	
	SerializeToDatabase STD = new SerializeToDatabase();
	/* Verify the user information in the database. If correct information, extract the information of 
	 * the user so that he/she can login. If the user gives the wrong password, but existing username,
	 * inform user that there is an invalid password. If the user gives a non-existing username, then
	 * inform the user that the username does not exist in the database  */
	public User getUser(String userToFind, String passwordToMatch) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		User user = null;
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
			
			ps = conn.prepareStatement("SELECT u.username, u.userPassword, u.userLevel, u.userWins, u.userLosses "
										+ " FROM ProjectUserTable u " + " WHERE u.username=? " + ";");
										//+ " AND u.userPassword=? " + ";");
			
			//userToFind = "rayandrie"; // This is supposed to be the username passed in from the front end
			//passwordToMatch = "qwerty"; // This is supposed to be the password passed in from the front end
			
			// USERNAMES SHOULD BE UNIQUE
			
			ps.setString(1, userToFind);
			
			System.out.println(userToFind + " " + passwordToMatch);
			
			// Embedding SQL Code
			rs = ps.executeQuery();
			
			// Get the data from the database so that user can log in
			
			String username = "";
			String password = "";
			int userLevel;
			int userWins;
			int userLosses;
			long serialized_id_player;
			long serialized_id_decks;
			Decks deck;
			Player player;
			
			if (rs.next() == true) // User exists in the database
			{
				if (passwordToMatch.equals(rs.getString("userPassword")))
				{
					username = userToFind;
					password = passwordToMatch;
					userLevel = rs.getInt("userLevel");
					userWins = rs.getInt("userWins");
					userLosses = rs.getInt("userLosses");
					
					PreparedStatement psi = null;
					
					psi = conn.prepareStatement("SELECT p.serialized_id_player FROM PlayerObject p WHERE p.username = ?;");
					psi.setString(1, userToFind);
					ResultSet rsi = psi.executeQuery();
					rsi.next();
					serialized_id_player = rsi.getLong("serialized_id_player");
					

					psi = conn.prepareStatement("SELECT d.serialized_id_decks FROM DecksObject d WHERE d.username = ?;");
					psi.setString(1, userToFind);
					rsi = psi.executeQuery();
					rsi.next();
					serialized_id_decks = rsi.getLong("serialized_id_decks");
					
					deck = (Decks) STD.deSerializeJavaObjectFromDB(conn, serialized_id_decks, SQL_DESERIALIZE_OBJECT_DECKS);
					player = (Player) STD.deSerializeJavaObjectFromDB(conn, serialized_id_player, SQL_DESERIALIZE_OBJECT_PLAYER);
										
					System.out.println("Username: " + username + ", Password: " + password + ", Level: "
										+ userLevel + ", User Wins: " + userWins + ", User Losses: " + userLosses);
					// Added statement
					//user = new User(deck, username, password, player, userWins, userLosses, userLevel);
					//user.printUsername();
					// Return null user
					user = new User(deck, username, password, player, userWins, userLosses, userLevel);
				}
				else // Here, username exists, but password is wrong
				{
					System.out.println("Invalid Password!");
				}
			}
			else // User does not exist in the database
			{
				System.out.println("User does not exist in the Database!");
			}
		}
		catch (SQLException io)
		{
			System.out.println("sqle: " + io.getMessage());
		}
		catch (ClassNotFoundException io)
		{
			System.out.println("cnfe: " + io.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		AccessSQLDatabase ASD = new AccessSQLDatabase();
		User user = ASD.getUser("Cats", "Dogs");
		//User user = ASD.getUser("Judy", "qwerty");
		//user.printUsername();
	}
}
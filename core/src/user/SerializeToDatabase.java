package user;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class SerializeToDatabase {

	private static final String SQL_SERIALIZE_OBJECT_PLAYER = "INSERT INTO PlayerObject(username, serialized_object_player) VALUES (?, ?)";
	private static final String SQL_SERIALIZE_OBJECT_DECKS = "INSERT INTO DecksObject(username, serialized_object_decks) VALUES (? , ?)";
	private static final String SQL_DESERIALIZE_OBJECT_PLAYER = "SELECT serialized_object_player FROM PlayerObject WHERE serialized_id_player = ?";
	private static final String SQL_DESERIALIZE_OBJECT_DECKS = "SELECT serialized_object_decks FROM DecksObject WHERE serialized_id_decks = ?";


	public static long serializeJavaObjectToDB(Connection connection,
			Object objectToSerialize, String username, String request) throws SQLException {

		PreparedStatement pstmt = connection
				.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);

		// just setting the class name
		pstmt.setString(1, username);
		pstmt.setObject(2, objectToSerialize);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		int serialized_id = -1;
		if (rs.next()) {
			serialized_id = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		System.out.println("Java object serialized to database. Object: "
				+ objectToSerialize);
		return serialized_id;
	}

	/**
	 * To de-serialize a java object from database
	 *
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deSerializeJavaObjectFromDB(Connection connection,
			long serialized_id, String request) throws SQLException, IOException,
			ClassNotFoundException {
		PreparedStatement pstmt = connection
				.prepareStatement(request);
		pstmt.setLong(1, serialized_id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();

		// Object object = rs.getObject(1);

		byte[] buf = rs.getBytes(1);
		ObjectInputStream objectIn = null;
		if (buf != null)
			objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));

		Object deSerializedObject = objectIn.readObject();

		rs.close();
		pstmt.close();

		System.out.println("Java object de-serialized from database. Object: "
				+ deSerializedObject + " Classname: "
				+ deSerializedObject.getClass().getName());
		return deSerializedObject;
	}


	/**
	 * Serialization and de-serialization of java object from mysql
	 *
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String args[]) throws ClassNotFoundException,
			SQLException, IOException {
		Connection connection = null;
		Class.forName("com.mysql.jdbc.Driver");
		
		String yourPassword = "Equyi86V";
		connection = DriverManager.getConnection("jdbc:mysql://localhost/ProjectUserDatabase?user=root&password=" + yourPassword + "&useSSL=false");

		// a sample java object to serialize
		Player player = new Player();
		long serialized_id = serializeJavaObjectToDB(connection, player, "Judy", SQL_SERIALIZE_OBJECT_PLAYER);

		// serializing java object to mysql database


		// de-serializing java object from mysql database
		Player objFromDatabase = (Player) deSerializeJavaObjectFromDB(
				connection, serialized_id, SQL_DESERIALIZE_OBJECT_PLAYER);
		connection.close();
	}
}

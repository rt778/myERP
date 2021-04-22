package connectionManager;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySQLConnection {

	private Connection connection;
	
	public MySQLConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myfirstdatabase?" +
		                       "user=root&password=coursjava");
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Opened database succesfully");
		}
	
	
	public Connection getConnection() {
		return connection;
	}
	

	public static void main(String[] args) {
		
		try {
			MySQLConnection c = new MySQLConnection();
			c.getConnection();
		} catch (Exception e) {
			// e.printStackTrace();
		}	
		

	}

	
}

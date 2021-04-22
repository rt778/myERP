package FreightManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import connectionManager.MySQLConnection;

public class FreightManagerSqlTable {

	private Connection myConnection;
	
	public void CreateFreightManagerSqlTable(){
		try {
			myConnection = new MySQLConnection().getConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			}
		String sqlCommand = "CREATE TABLE if not exists Freight ("
				+ "LoadPort VARCHAR(20) NOT NULL, "
				+ "Disport VARCHAR(20) NOT NULL, "
				+ "TotalFreight DOUBLE UNSIGNED NOT NULL, "
				+ "ValidityPeriodStart DATE NOT NULL,"
				+ "ValidityPeriodEnd DATE NOT NULL,"
				+ "Forwarder VARCHAR(30) NOT NULL);";
		try{
			Statement st = myConnection.createStatement();
			System.out.println(sqlCommand);
			st.executeUpdate(sqlCommand);
			st.close();		
		}
		catch (SQLException sqle){
			//sqle.printStackTrace();
		}
	}	
	
	
	
	public static void main(String[] args) {
		
		try {
		FreightManagerSqlTable freightTable = new FreightManagerSqlTable();
		freightTable.CreateFreightManagerSqlTable();
		} catch (Exception e){
			e.printStackTrace();
		}
		

	}

}

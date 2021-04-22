package inventoryManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectionManager.MySQLConnection;
import interfaceManager.AllocateToContractForm;
import interfaceManager.InventoryJTable;


public class InventorySQLTable {
	

	private static Connection myConnection;

	
	public void createInventorySQLTable(){
		try {
			myConnection = new MySQLConnection().getConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			}
		String sqlCommand = "CREATE TABLE if not exists Inventory"
				+ "(LotNumber VARCHAR(20) PRIMARY KEY NOT NULL, "
				+ "ProductStored VARCHAR(20) NOT NULL, "
				+ "QuantityStored DOUBLE UNSIGNED NOT NULL, "
				+ "EntryDate DATE NOT NULL,"
				+ "ExitDate DATE DEFAULT NULL,"
				+ "StorageLocation VARCHAR(50) NOT NULL, "
				+ "SoldToContractNumber VARCHAR(20));";
				
		try{
			Statement st = myConnection.createStatement();
			st.executeUpdate(sqlCommand);
			st.close();		
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}	
	
	
	public void addInventoryToSQLTable (Inventory inv){
		
		try {
			myConnection = new MySQLConnection().getConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			}
		
		String sqlCommand = "INSERT INTO Inventory ("
				+ "LotNumber, "
				+ "ProductStored, "
				+ "QuantityStored, "
				+ "EntryDate, "
				+ "ExitDate, "
				+ "StorageLocation, "
				+ "SoldToContractNumber) "
				+ "VALUES (\"" 
				+ inv.getLotNumber() + "\", \""
				+ inv.getProductStored() + "\",\"" 
				+ inv.getQuantityStored() + "\",\"" 
				+ inv.getEntryDate() + "\"," 
				+ inv.getExitDate() + ",\""
				+ inv.getStorageLocation() + "\", \""
				+ inv.getSoldToContractNumber() + "\")";
		try{
			System.out.println(sqlCommand);
			Statement st = myConnection.createStatement();
			st.executeUpdate(sqlCommand);
			st.close();		
		}
		catch (SQLException sqle){
			//sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "Something went wrong. Please verify your inputs.");

		}
		
	}
	
	
public void deleteInventoryFromSQLTable (Inventory inv){
		
		try {
			myConnection = new MySQLConnection().getConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			}
		
		String sqlCommand = "DELETE FROM Inventory WHERE LotNumber = \"" + inv.getLotNumber() + "\";";
		System.out.println(sqlCommand);
		try{
			Statement st = myConnection.createStatement();
			st.executeUpdate(sqlCommand);
			st.close();		
		}
		catch (SQLException sqle){
			//sqle.printStackTrace();
		}
		
	}
	
public static void allocateLotToSales(){
	System.out.println("Calling allocateLotToSales from inventoryManager.InventorySQLTable.");
	
	System.out.println("Sales Contract to allocate: " + AllocateToContractForm.contractId);
	System.out.println("Lot number being allocated: " + InventoryJTable.valueInventoryJTableColumn0);
	
	String sqlCommand = "UPDATE Inventory SET SoldToContractNumber = \""
	+ AllocateToContractForm.contractId  + "\" WHERE LotNumber = \"" 
	+ InventoryJTable.valueInventoryJTableColumn0 + "\";";
	System.out.println(sqlCommand);
	
	try {
		myConnection = new MySQLConnection().getConnection();
	} catch (Exception e) {
		//e.printStackTrace();
		}
	
	try{
		Statement st = myConnection.createStatement();
		st.executeUpdate(sqlCommand);
		st.close();	
	}
	catch (SQLException sqle){
		//sqle.printStackTrace();
	}	
	
	
}
	

	
public List<Inventory> readDbInventoryTable(){

		
		String sqlCommand = "SELECT * FROM Inventory";
		List<Inventory>  listOfInventory = new ArrayList<Inventory>();

		
		try{
			Statement statement1 = myConnection.createStatement();
			ResultSet resultSet1 = statement1.executeQuery(sqlCommand);
			while (resultSet1.next()){
				
			
				String lotNumber = resultSet1.getString("LotNumber");
				String productStored = resultSet1.getString("ProductStored");
				double quantityStored = resultSet1.getDouble("QuantityStored");
				String entryDate = resultSet1.getString("EntryDate");
				String exitDate = resultSet1.getString("ExitDate");
				String storageLocation = resultSet1.getString("StorageLocation");
				String soldToContractNumber = resultSet1.getString("SoldToContractNumber");
				
				Inventory inv = new Inventory();
						inv.setLotNumber(lotNumber); 
						inv.setProductStored(productStored);
						inv.setQuantityStored(quantityStored);
						inv.setEntryDate(entryDate);
						inv.setExitDate(exitDate);
						inv.setStorageLocation(storageLocation);
						inv.setSoldToContractNumber(soldToContractNumber);
						
				listOfInventory.add(inv);
			}
			resultSet1.close();
			statement1.close();
			return listOfInventory ;
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
			return listOfInventory;
		}

	}
	
	
	
	public static void main(String[] args) {
			
		
		try{		
			InventorySQLTable inventoryTable = new InventorySQLTable();
			inventoryTable.createInventorySQLTable();
			
			Inventory inv1 = new Inventory();
			
			inv1.setEntryDate("2017-07-21");
			//inv1.setExitDate("'2017-07-22'");// we need to quote 2 times here like this: "''";
			inv1.setLotNumber("17-07211143");
			inv1.setProductStored("BK");
			inv1.setQuantityStored(25.92);
			inv1.setStorageLocation("STP");
			
			inventoryTable.addInventoryToSQLTable(inv1);
				
			
			}
		catch (Exception e)
		{
			//e.printStackTrace();
		}
		

		
	}
	
}

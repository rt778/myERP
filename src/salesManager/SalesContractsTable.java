package salesManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectionManager.MySQLConnection;


public class SalesContractsTable {
	

	private Connection myConnection;

	
	public void CreateSalesContractsTable(){
		try {
			myConnection = new MySQLConnection().getConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			}
		String sqlCommand = "CREATE TABLE if not exists SalesContracts"
				+ "(ContractId TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
				+ "ContractDate DATE NOT NULL,"
				+ "Counterparty VARCHAR(50) NOT NULL, "
				+ "Quantity DOUBLE UNSIGNED NOT NULL, "
				+ "Product VARCHAR(20) NOT NULL, "
				+ "Price DOUBLE UNSIGNED NOT NULL, "
				+ "DeliveryTerms VARCHAR(20) NOT NULL, "
				+ "DeliveryPeriodStart DATE NOT NULL,"
				+ "DeliveryPeriodEnd DATE NOT NULL,"
				+ "PaymentTerms VARCHAR(30) NOT NULL);";
		try{
			Statement st = myConnection.createStatement();
			st.executeUpdate(sqlCommand);
			st.close();		
		}
		catch (SQLException sqle){
			//sqle.printStackTrace();
		}
	}	
	

	
	
	
	public void addSalesContractsToTable (SalesContracts SC){
		
		try {
			myConnection = new MySQLConnection().getConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			}
		
		String sqlCommand = "INSERT INTO SalesContracts ("
				+ "ContractDate, "
				+ "Counterparty, "
				+ "Quantity, "
				+ "Product, "
				+ "Price, "
				+ "DeliveryTerms, "
				+ "DeliveryPeriodStart, "
				+ "DeliveryPeriodEnd, "
				+ "PaymentTerms) "
				+ "VALUES (\"" 
				+ SC.getContractDate() + "\", \""
				+ SC.getCounterpartyName() + "\",\"" 
				+ SC.getQuantitySold() + "\",\"" 
				+ SC.getProductSold() + "\",\"" 
				+ SC.getPrice()+ "\",\"" 
				+ SC.getDeliveryTerms()+ "\",\"" 
				+ SC.getDeliveryPeriodStart()+ "\",\"" 
				+ SC.getDeliveryPeriodEnd()+ "\",\"" 	
				+ SC.getPaymentTerms() + "\")";
		try{
			Statement st = myConnection.createStatement();
			st.executeUpdate(sqlCommand);
			st.close();		
		}
		catch (SQLException sqle){
			//sqle.printStackTrace();
			JOptionPane.showMessageDialog(null, "No empty field allowed. Please verify your inputs.");

		}
		
	}
	
	
	
	
public void deleteSalesContractsFromTable (SalesContracts SC){
		
		try {
			myConnection = new MySQLConnection().getConnection();
		} catch (Exception e) {
			//e.printStackTrace();
			}
		
		String sqlCommand = "DELETE FROM SalesContracts WHERE ContractId = \"" + SC.getContractId() + "\";";
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
	
	

	
public List<SalesContracts> readDbSalesTable(){

		
		String sqlCommand = "SELECT * FROM SalesContracts";
		List<SalesContracts>  listOfSalesContracts = new ArrayList<SalesContracts>();

		
		try{
			Statement statement1 = myConnection.createStatement();
			ResultSet resultSet1 = statement1.executeQuery(sqlCommand);
			while (resultSet1.next()){
				
			
				String contractId = resultSet1.getString("ContractId");
				String contractDate = resultSet1.getString("ContractDate");
				String counterpartyName = resultSet1.getString("Counterparty");
				double quantitySold = resultSet1.getDouble("Quantity");
				String productSold = resultSet1.getString("Product");
				double price = resultSet1.getDouble("Price");
				String deliveryTerms = resultSet1.getString("DeliveryTerms");
				String deliveryPeriodStart = resultSet1.getString("DeliveryPeriodStart");
				String deliveryPeriodEnd = resultSet1.getString("DeliveryPeriodEnd");
				String paymentTerms =  resultSet1.getString("PaymentTerms");
				
				
				SalesContracts SC = new SalesContracts();
						SC.setContractId(contractId); 
						SC.setContractDate(contractDate); 
						SC.setCounterpartyName(counterpartyName); 
						SC.setQuantitySold(quantitySold); 
						SC.setProductSold(productSold);
						SC.setPrice(price);
						SC.setDeliveryTerms(deliveryTerms);
						SC.setDeliveryPeriodStart(deliveryPeriodStart);
						SC.setDeliveryPeriodEnd(deliveryPeriodEnd);
						SC.setPaymentTerms(paymentTerms);
				
				listOfSalesContracts.add(SC);
			}
			resultSet1.close();
			statement1.close();
			return listOfSalesContracts ;
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
			return listOfSalesContracts;
		}

	}
	
	
	
	

	
	public static void main(String[] args) {
			
		
		try{		
			SalesContractsTable salesTable = new SalesContractsTable();
			salesTable.CreateSalesContractsTable();
			
			SalesContracts ct1 = new SalesContracts();
			
			ct1.setContractDate("2017-07-17");
			ct1.setCounterpartyName("Citytrade");
			ct1.setProductSold("BK");
			ct1.setQuantitySold(25.92);
			ct1.setPrice(1500.00);
			ct1.setDeliveryTerms("CIF Shanghai");
			ct1.setDeliveryPeriodStart("2017-08-01");
			ct1.setDeliveryPeriodEnd("2017-08-31");
			ct1.setPaymentTerms("L/C at sight");
			
			salesTable.addSalesContractsToTable (ct1);
			
			
			
			}
		catch (Exception e)
		{
			//e.printStackTrace();
		}
		

		
	}
	
}

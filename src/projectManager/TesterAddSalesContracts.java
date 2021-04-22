package projectManager;

import salesManager.SalesContracts;
import salesManager.SalesContractsTable;

public class TesterAddSalesContracts {

	public static void main(String[] args) {
		
		try{	
			SalesContractsTable salesTable = new SalesContractsTable();
			salesTable.CreateSalesContractsTable();
			
			SalesContracts ct2 = new SalesContracts();
			
			ct2.setContractDate("2017-07-20");
			ct2.setCounterpartyName("EchoTrade");
			ct2.setProductSold("SKDN");
			ct2.setQuantitySold(51.84);
			ct2.setPrice(950.00);
			ct2.setDeliveryTerms("CIF Barcelona");
			ct2.setDeliveryPeriodStart("2017-08-01");
			ct2.setDeliveryPeriodEnd("2017-08-31");
			ct2.setPaymentTerms("Document Presentation");
			
			salesTable.addSalesContractsToTable(ct2);
			
			
			}
		catch (Exception e)
			{
			e.printStackTrace();
			}

		}
	}

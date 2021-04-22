package projectManager;


import java.util.List;

import salesManager.SalesContracts;
import salesManager.SalesContractsTable;

public class TesterDisplaySalesContractsInConsole {


	public static void main(String[] args) {
		
		try{	
			SalesContractsTable salesTable = new SalesContractsTable();
			salesTable.CreateSalesContractsTable();
			
			List<SalesContracts> LSC = salesTable.readDbSalesTable();
			for (SalesContracts salesContracts: LSC){
				System.out.printf("%s   %s  %.2f  %4s  %8s   %-15s %s  %s  %s\n",
						salesContracts.getContractId(),
						salesContracts.getContractDate(),
						salesContracts.getQuantitySold(),
						salesContracts.getProductSold(),
						salesContracts.getPrice(),
						salesContracts.getDeliveryTerms(),
						salesContracts.getDeliveryPeriodStart(),
						salesContracts.getDeliveryPeriodEnd(),
						salesContracts.getPaymentTerms()
						);
			}
			
			}
		catch (Exception e)
			{
			e.printStackTrace();
			}

		}
	}

package projectManager;

import salesManager.SalesContracts;
import salesManager.SalesContractsTable;

public class TesterDeleteSalesContracts {

	public static void main(String[] args) {
		
		try{	
			SalesContractsTable salesTable = new SalesContractsTable();
			salesTable.CreateSalesContractsTable();
			
			
			SalesContracts ct3 = new SalesContracts();
			ct3.setContractId("2017-07-17 10:35:09");
			System.out.println(ct3.getContractId());
			salesTable.deleteSalesContractsFromTable(ct3);
			
			}
		catch (Exception e)
			{
			e.printStackTrace();
			}

		}
	}

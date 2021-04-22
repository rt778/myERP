package interfaceManager;

import java.awt.*;
import javax.swing.*;
import connectionManager.MySQLConnection;
import net.proteanit.sql.DbUtils;
import java.sql.*;

public class StuffingDetailsJTable {
									
	private JFrame frame;
	private JPanel panel;
	public static JButton btnLoadInventory;
	private static JTable table;
	private static Connection myConnection;
	private JScrollPane scrollPane_1;
	public String contractIdToStuff;
	
	
	public static JTable getStuffingDetails(String contractIdToStuff){
		
		try {
			myConnection = new MySQLConnection().getConnection();
		} catch (Exception e1) {
			//e.printStackTrace();
			System.out.println("Exception line 42 of StuffingDetailsJTable catched");
		}

		try {
			
			String sqlCommand = "select Inventory.lotNumber, "
					+ "Inventory.productStored, "
					+ "Inventory.quantityStored, "
					+ "SalesContracts.ContractId, "
					+ "SalesContracts.Counterparty "
					+ "from "
					+ "Inventory, "
					+ "SalesContracts "
					+ "where Inventory.SoldToContractNumber = SalesContracts.ContractId "
					+ "&& "
					+ "Inventory.SoldToContractNumber = " + "'" + contractIdToStuff + "'" + ";";
					
			System.out.println(sqlCommand);
			
			PreparedStatement statement1 = myConnection.prepareStatement(sqlCommand);
			ResultSet resultSet1 = statement1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultSet1));

		} catch (Exception e2) {
			// TODO: handle exception
		}	
		return table;
	}
	

	
	////// MAIN
	public static void main(String[] args) {
		

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StuffingDetailsJTable window = new StuffingDetailsJTable();
					window.frame.setVisible(true);
					StuffingDetailsJTable.getStuffingDetails(SalesContractsJTable.contractId);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		

	}

	//// END MAIN

	
	public StuffingDetailsJTable() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame("Stuffing Details For Referenced Contract");
		frame.setBounds(300, 500, 1000, 150);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 512, 175, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 29, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
		// add new composant here as needed
		
		//
		
		// scrollPane
		scrollPane_1 = new JScrollPane();
		frame.getContentPane().add(scrollPane_1);

		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(table);

	}


}

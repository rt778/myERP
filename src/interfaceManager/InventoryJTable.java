package interfaceManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import connectionManager.MySQLConnection;
import inventoryManager.Inventory;
import inventoryManager.InventorySQLTable;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.util.List;

public class InventoryJTable implements ListSelectionListener {
										// List selection listerner is useful so that we can select a row with the mouse
	private JFrame frame;
	private JPanel panel;
	public static JButton btnLoadInventory;
	private JTable table;
	private Connection myConnection;
	private JScrollPane scrollPane_1;
	private JButton btnAddInventory;
	private JButton btnDeleteInventory;
	private static boolean ignoreEvent = false ;
	public static String valueInventoryJTableColumn0;
	public static String valueInventoryJTableColumn1;
	public static Double valueInventoryJTableColumn2;
	public static Date valueInventoryJTableColumn3;
	public static String valueInventoryJTableColumn5;
	private JButton btnAllocateToContract;

	/**
	 * Launch the application.
	 */

	
	// The valueChanged method is for selecting the lot number with mouse
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		
		//This is a quick fix because we get the selected lot number twice.
		if (!ignoreEvent) {
			ignoreEvent = !ignoreEvent ;
		}
		else {
			ignoreEvent = !ignoreEvent ;
			return ;
		}
		//
		
		
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();

		int firstIndex = lsm.getMinSelectionIndex();

		try {
			System.out.println("Selected row =" + firstIndex);
			
			valueInventoryJTableColumn0 = (String) table.getModel().getValueAt(firstIndex, 0);
			System.out.println("Selected value of column 0: " + valueInventoryJTableColumn0);
			
			valueInventoryJTableColumn1 = (String) table.getModel().getValueAt(firstIndex, 1);
			System.out.println("Selected value of column 1: " + valueInventoryJTableColumn1);
			
			valueInventoryJTableColumn2 = (Double) table.getModel().getValueAt(firstIndex, 2);
			System.out.println("Selected value of column 2: " + valueInventoryJTableColumn2);
			
			valueInventoryJTableColumn3 = (Date) table.getModel().getValueAt(firstIndex, 3);
			System.out.println("Selected value of column 3: " + valueInventoryJTableColumn3);
			
			valueInventoryJTableColumn5 = (String) table.getModel().getValueAt(firstIndex, 5);
			System.out.println("Selected value of column 5: " + valueInventoryJTableColumn5);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception line 80 catched");
		}
	}

	////// MAIN
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryJTable window = new InventoryJTable();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		try {
			InventorySQLTable inventoryTable = new InventorySQLTable();
			inventoryTable.createInventorySQLTable();

			List<Inventory> LOI = inventoryTable.readDbInventoryTable();
			for (Inventory inventory : LOI) {
				System.out.printf("%s   %s  %.2f  %s  %s  %s  %s\n", inventory.getLotNumber(),
						inventory.getProductStored(), inventory.getQuantityStored(), inventory.getEntryDate(),
						inventory.getExitDate(), inventory.getStorageLocation(), inventory.getSoldToContractNumber());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//// END MAIN

	/**
	 * Create the application.
	 */
	public InventoryJTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Inventory");
		frame.setBounds(200, 405, 1200, 300);
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
		
		
		//Button to load Inventory
		btnLoadInventory = new JButton("Load Inventory");
		btnLoadInventory.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					myConnection = new MySQLConnection().getConnection();
				} catch (Exception e1) {
					//e.printStackTrace();
					System.out.println("Exception line 154 catched");
				}

				try {

					String sqlCommand = "SELECT * FROM Inventory";
					PreparedStatement statement1 = myConnection.prepareStatement(sqlCommand);
					ResultSet resultSet1 = statement1.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultSet1));

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		//

		// Button to allocate to contracts
		
		
		btnAllocateToContract = new JButton("Allocate to Contract");
		btnAllocateToContract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("btnAllocateToContract is being clicked");
				try{
				AllocateToContractForm allocateToContractForm1 = new AllocateToContractForm();
				allocateToContractForm1.frame.setVisible(true);
				
				}catch(Exception e3){
				e3.printStackTrace();
				System.out.println("Please select the lot to allocate first");
				JOptionPane.showMessageDialog(null, "Please select the lot to allocate first.");
			}
				
			}
		});
		
		GridBagConstraints gbc_btnAllocateToContract = new GridBagConstraints();
		gbc_btnAllocateToContract.gridx=1;
		panel.add(btnAllocateToContract,gbc_btnAllocateToContract);
		
	
		
		//
		
		
		//Button to delete the Inventory
		btnDeleteInventory = new JButton("Delete Inventory");
		btnDeleteInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteInventoryForm();

			}
		});

		GridBagConstraints gbc_btnDeleteInventory = new GridBagConstraints();
		gbc_btnDeleteInventory.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteInventory.gridx = 9;
		gbc_btnDeleteInventory.gridy = 0;
		panel.add(btnDeleteInventory, gbc_btnDeleteInventory);
		//
		

		// Button to add inventory
		btnAddInventory = new JButton("Add Inventory");
		
		
		btnAddInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//addNewInventoryForm();
				AddNewInventoryForm newInventoryForm1 = new AddNewInventoryForm();
				newInventoryForm1.frame.setVisible(true);
			}
		});

		GridBagConstraints gbc_btnAddInventory = new GridBagConstraints();
		gbc_btnAddInventory.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddInventory.gridx = 10;
		gbc_btnAddInventory.gridy = 0;
		panel.add(btnAddInventory, gbc_btnAddInventory);

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.gridx = 11;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnLoadInventory, gbc_btnNewButton);
		//

		
		
		// scrollPane
		scrollPane_1 = new JScrollPane();
		frame.getContentPane().add(scrollPane_1);

		table = new JTable();
		table.setFillsViewportHeight(true);
		
		// The next two lines are useful to select the lot number with the mouse
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		//
		
		scrollPane_1.setViewportView(table);

	}

	// method that adds new contract from the Jtable;
	private static void addNewInventoryForm() {

		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JTextField field3 = new JTextField();
		JTextField field4 = new JTextField();
		JTextField field5 = new JTextField();
		
		
		Object[] message = {

				"Lot Number:                                      ", field1, 
				"Product Stored:", field2,
				"Quantity Stored:", field3, 
				"Entry Date:", field4,	
				"Storage Location:", field5, };

		int option = JOptionPane.showConfirmDialog(null, message, "Input New Inventory", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {

			String value1 = field1.getText();
			String value2 = field2.getText();
			double value3 = Double.parseDouble(field3.getText());
			String value4 = field4.getText();
			String value5 = field5.getText();

			try {
				InventorySQLTable inventoryTable = new InventorySQLTable();
				inventoryTable.createInventorySQLTable();

				Inventory inv2 = new Inventory();

				inv2.setLotNumber(value1);
				inv2.setProductStored(value2);
				inv2.setQuantityStored(value3);
				inv2.setEntryDate(value4);
				inv2.setStorageLocation(value5);

				inventoryTable.addInventoryToSQLTable(inv2);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	//
	

	// method that show the form to delete a lot
	private static void deleteInventoryForm() {

		JTextField field1 = new JTextField(valueInventoryJTableColumn0);

		Object[] message = {
				"Lot Number of inventory to delete:", field1,
		};

		int option = JOptionPane.showConfirmDialog(null, message, "Input Inventory Lot Number",
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			
			

			String value1 = field1.getText();

			try {
				InventorySQLTable inventoryTable = new InventorySQLTable();
				inventoryTable.createInventorySQLTable();

				Inventory inv2 = new Inventory();

				inv2.setLotNumber(value1);

				inventoryTable.deleteInventoryFromSQLTable(inv2);

			} catch (Exception e) {
				//e.printStackTrace();
			}

		}

	}

}

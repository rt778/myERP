package interfaceManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import connectionManager.MySQLConnection;
import net.proteanit.sql.DbUtils;
import salesManager.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;


public class SalesContractsJTable implements ListSelectionListener {

	private JFrame frame;
	private JPanel panel;
	private JButton btnLoadContract;
	private JTable table;
	private Connection myConnection;
	private JScrollPane scrollPane_1;
	private JButton btnAddContracts;
	private JButton btnDeleteContracts;
	private static boolean ignoreEvent = false ;
	public static Timestamp valueOfSalesContractsJTableColumn0;
	public static String contractId;
	private JButton btnShowStuffingDetails;

	
	
	// this the the implemented method for the ListSelectionListener
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
			
			valueOfSalesContractsJTableColumn0 = (Timestamp) table.getModel().getValueAt(firstIndex, 0);
			System.out.println("Selected value of column 0: " + valueOfSalesContractsJTableColumn0);

			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception valueChanged method in SalesContractsJTable catched");
		}
		
	}	
	


	/**
	 * Launch the application.
	 */
	
	
	
	//////MAIN
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesContractsJTable window = new SalesContractsJTable();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
		
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

	////END MAIN
	
	
	/**
	 * Create the application.
	 */
	public SalesContractsJTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Sales Contracts");
		frame.setBounds(200, 100, 1200, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{512, 175, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{29, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		
		
		
		//button to load contracts
		btnLoadContract = new JButton("Load Sales Contracts");
		btnLoadContract.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					myConnection = new MySQLConnection().getConnection();
				} catch (Exception e1) {
					//e.printStackTrace();
					}
				
			
				try {
					
					String sqlCommand = "SELECT * FROM SalesContracts";
					PreparedStatement statement1 = myConnection.prepareStatement(sqlCommand);
					ResultSet resultSet1 = statement1.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultSet1));
					
					

					//table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

					
					table.getColumnModel().getColumn(0).setPreferredWidth(160);
					table.getColumnModel().getColumn(1).setPreferredWidth(100);
					table.getColumnModel().getColumn(2).setPreferredWidth(120);
					table.getColumnModel().getColumn(3).setPreferredWidth(100);
					table.getColumnModel().getColumn(4).setPreferredWidth(100);
					table.getColumnModel().getColumn(5).setPreferredWidth(100);
					table.getColumnModel().getColumn(6).setPreferredWidth(120);
					table.getColumnModel().getColumn(7).setPreferredWidth(120);
					table.getColumnModel().getColumn(8).setPreferredWidth(120);
					table.getColumnModel().getColumn(9).setPreferredWidth(150);


					
					DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Double.class);
					renderer.setHorizontalAlignment( JLabel.RIGHT);
					
					DefaultTableCellRenderer renderer1 = (DefaultTableCellRenderer)table.getDefaultRenderer(String.class);
					renderer1.setHorizontalAlignment( JLabel.RIGHT);
				
					((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer())
				    .setHorizontalAlignment(JLabel.RIGHT);

					
				} catch (Exception e2) {
					// TODO: handle exception
				}				
			}
		});
		
		GridBagConstraints gbc_btnLoadContract = new GridBagConstraints();
		//gbc_btnLoadContract.anchor = GridBagConstraints.NORTHEAST;
		//gbc_btnLoadContract.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoadContract.gridx = 4;
		gbc_btnLoadContract.gridy = 0;
		panel.add(btnLoadContract, gbc_btnLoadContract);
		//
		
		
		// button delete contract
		btnDeleteContracts = new JButton("Delete Contracts");
		btnDeleteContracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSalesContractsForm();
						
			}
		});
		
		
		
		GridBagConstraints gbc_btnDeleteContracts = new GridBagConstraints();
		gbc_btnDeleteContracts.insets = new Insets(0, 0, 0, 5);
		gbc_btnDeleteContracts.gridx = 2;
		gbc_btnDeleteContracts.gridy = 0;
		panel.add(btnDeleteContracts, gbc_btnDeleteContracts);
		//
		
		
		//Button Add Contracts
		btnAddContracts = new JButton("Add Contracts");
		btnAddContracts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showNewSalesContractsForm();
				
			}
		});
		
		
		GridBagConstraints gbc_btnAddContracts = new GridBagConstraints();
		gbc_btnAddContracts.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddContracts.gridx = 3;
		gbc_btnAddContracts.gridy = 0;
		panel.add(btnAddContracts, gbc_btnAddContracts);
		
		
		
		
		
		// button to Show Stuffing Details
		
		btnShowStuffingDetails = new JButton("Show Stuffing Details");
		btnShowStuffingDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("The button btnShowStuffingDetails has been clicked.");
				contractId = convertTimestampToString(valueOfSalesContractsJTableColumn0);
				System.out.println(contractId);
				StuffingDetailsJTable.main(null);
				
						
			}
		});
		
		GridBagConstraints gbc_btnShowStuffingDetails = new GridBagConstraints();
		gbc_btnShowStuffingDetails.insets = new Insets(0, 0, 0, 5);
		gbc_btnShowStuffingDetails.gridx = 1;
		gbc_btnShowStuffingDetails.gridy = 0;
		panel.add(btnShowStuffingDetails, gbc_btnShowStuffingDetails);
		//
		
		
		//scrollPane_1 = new JScrollPane();
		scrollPane_1 = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scrollPane_1);
 
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane_1.setViewportView(table);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);



		// The next two lines are useful to select the lot number with the mouse
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		//
	}
	
	
	
	// method that adds new contract from the Jtable;
	private static void showNewSalesContractsForm(){
		
		JTextField field1 = new JTextField();
		JTextField field2 = new JTextField();
		JTextField field3 = new JTextField();
		JTextField field4 = new JTextField();
		JTextField field5 = new JTextField();
		JTextField field6 = new JTextField();
		JTextField field7 = new JTextField();
		JTextField field8 = new JTextField();
		JTextField field9 = new JTextField();
		
		
		Object[] message = {
				
				"Contract Date:                                      ", field1,
				"Counterparty:", field2,
				"Product:", field3,
				"Quantity:", field4,
				"Price:", field5,
				"Delivery Terms:", field6,
				"Delivery Period Start:", field7,
				"Delivery Period End:", field8,
				"PaymentTerms:",field9
		};
		
		int option = JOptionPane.showConfirmDialog (null, message, "Input New Contract", JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION){
			
			String value1 = field1.getText();
			String value2 = field2.getText();
			String value3 = field3.getText();
			double value4 = Double.parseDouble(field4.getText());
			double value5 = Double.parseDouble(field5.getText());
			String value6 = field6.getText();
			String value7 = field7.getText();
			String value8 = field8.getText();
			String value9 = field9.getText();
			
			try{	
				SalesContractsTable salesTable = new SalesContractsTable();
				salesTable.CreateSalesContractsTable();
				
				SalesContracts ct2 = new SalesContracts();
				
				ct2.setContractDate(value1);
				ct2.setCounterpartyName(value2);
				ct2.setProductSold(value3);
				ct2.setQuantitySold(value4);
				ct2.setPrice(value5);
				ct2.setDeliveryTerms(value6);
				ct2.setDeliveryPeriodStart(value7);
				ct2.setDeliveryPeriodEnd(value8);
				ct2.setPaymentTerms(value9);
				
				salesTable.addSalesContractsToTable(ct2);
				
			}
			catch (Exception e)
				{
				e.printStackTrace();
				}
			
			
		}
		
	}
	
	// method to delete a contract
private static void deleteSalesContractsForm(){
	
		contractId = convertTimestampToString(valueOfSalesContractsJTableColumn0);
		System.out.println(contractId);
		
		JTextField field1 = new JTextField(contractId);
		
		
		Object[] message = {
				
				"Contract ID of contract to delete:", field1,
	
		};
		
		int option = JOptionPane.showConfirmDialog (null, message, "Input ContractId", JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION){
			
			String value1 = field1.getText();
			
			
			try{	
				SalesContractsTable salesTable = new SalesContractsTable();
				salesTable.CreateSalesContractsTable();
				
				SalesContracts ct2 = new SalesContracts();
				
				ct2.setContractId(value1);
				
				
				salesTable.deleteSalesContractsFromTable(ct2);
				
			}
			catch (Exception e)
				{
				e.printStackTrace();
				}
			
			
		}
		
	}


//Convert Date to String
	public static String convertTimestampToString(Timestamp inTimestamp){
		String timestampString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			timestampString = sdfr.format(inTimestamp);
		}catch (Exception ex){
			System.out.println(ex);
		}
		
		return timestampString;
	}
	
	
	
	
}





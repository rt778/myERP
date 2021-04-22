package interfaceManager;


import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inventoryManager.Inventory;
import inventoryManager.InventorySQLTable;

public class AllocateToContractForm {

	JFrame frame;
	
	private JPanel panelLotNumber;
	private JLabel labelLotNumber;
	private JTextField lotNumberTextField;
	
	private JPanel panelProductStored;
	private JLabel labelProductStored;
	private JTextField productStoredTextField;
	
	private JPanel panelQuantityStored;
	private JLabel labelQuantityStored;
	private JTextField quantityStoredTextField;
	
	private JLabel labelEntryDate;
	private JPanel panelEntryDate;
	private JTextField entryDateTextField;
	
	private JPanel panelStorageLocation;
	private JLabel labelStorageLocation;
	private JTextField storageLocationTextField;
	
	private JPanel panelSalesContractForAllocation;
	private JLabel labelSalesContractForAllocation;
	private JTextField salesContractForAllocationTextField;
	public static String contractId;
	
	private JPanel panelButtons;
	private JButton buttonCancel;
	private JButton buttonAllocate;
	private JLabel labelAlign;

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllocateToContractForm window = new AllocateToContractForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Please select the lot to allocate first");
					JOptionPane.showMessageDialog(null, "Please select the lot to allocate first.");

				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AllocateToContractForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// The main frame
		frame = new JFrame("Allocate Lots to Contract");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setLayout(new GridLayout(7,1));
		frame.setLocationRelativeTo(buttonAllocate);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		
		// The panel Lot Number
		panelLotNumber = new JPanel();
		
		labelLotNumber = new JLabel("                           Lot Number:", JLabel.RIGHT);
		panelLotNumber.add(labelLotNumber);
		
		lotNumberTextField = new JTextField(InventoryJTable.valueInventoryJTableColumn0,17);
		panelLotNumber.add(lotNumberTextField);
		
		
		panelLotNumber.setVisible(true);
		frame.add(panelLotNumber);
		//
		
		
		
		//The Panel Product Stored
	    panelProductStored = new JPanel();
	    
	    labelProductStored = new JLabel("                      Product Stored:", JLabel.RIGHT);
	    panelProductStored.add(labelProductStored);
	    panelProductStored.setVisible(true);
	    
	    productStoredTextField = new JTextField(InventoryJTable.valueInventoryJTableColumn1,17);
	    panelProductStored.add(productStoredTextField);
	    frame.add(panelProductStored);
	    //
		
		
	    // The quantity stored Panel
	    panelQuantityStored = new JPanel();
	    
	    
	    labelQuantityStored = new JLabel("                    Quantity Stored:", JLabel.RIGHT);
	    panelQuantityStored.add(labelQuantityStored);
	    panelQuantityStored.setVisible(true);
	    
	    quantityStoredTextField = new JTextField(Double.toString(InventoryJTable.valueInventoryJTableColumn2),17);
	    panelQuantityStored.add(quantityStoredTextField);
	    frame.add(panelQuantityStored);
	    //
	    
	    
		
		//The Panel Entry Date
		panelEntryDate = new JPanel();
			
		labelEntryDate = new JLabel("                            Entry Date:",JLabel.RIGHT);
		panelEntryDate.add(labelEntryDate);
		
		entryDateTextField = new JTextField(convertDateToString(InventoryJTable.valueInventoryJTableColumn3),17); 
        panelEntryDate.add(entryDateTextField); 
        
	    
        panelEntryDate.setVisible(true);
        frame.add(panelEntryDate);
	    //
	    
        // The storageLocation Panel
	    panelStorageLocation = new JPanel();
	    
	    labelStorageLocation = new JLabel("                  Storage Location:", JLabel.RIGHT);
	    panelStorageLocation.add(labelStorageLocation);
	    panelStorageLocation.setVisible(true);
	    
	    storageLocationTextField = new JTextField(InventoryJTable.valueInventoryJTableColumn5,17);
	    panelStorageLocation.add(storageLocationTextField);
	    frame.add(panelStorageLocation);
	    //
        
	    //---------------------------
	    
	    //The sales contract for allocation
	    
	    panelSalesContractForAllocation = new JPanel();
	    
	    labelSalesContractForAllocation = new JLabel("Sales Contract to allocate to:", JLabel.RIGHT);
	    panelSalesContractForAllocation.add(labelSalesContractForAllocation);
	    contractId = SalesContractsJTable.convertTimestampToString(SalesContractsJTable.valueOfSalesContractsJTableColumn0);
	    salesContractForAllocationTextField = new JTextField(contractId,17);
	    panelSalesContractForAllocation.add(salesContractForAllocationTextField);
	    
	    frame.add(panelSalesContractForAllocation);
	    
	    
	    
	    

        
        // The buttons panel
        panelButtons = new JPanel();
        
        	// The Cancel Button
        	buttonCancel = new JButton("Cancel");
        	labelAlign = new JLabel("                                      ");
        	panelButtons.add(labelAlign);// I use it to push the buttons into chosen positions
        	panelButtons.add(buttonCancel);
        	buttonCancel.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				//write here things to do
    				System.out.println("Cancel Button clicked");
    				frame.dispose();
    			}
    		});
        	//
        	
        	
        	// The Allocate Button
        	buttonAllocate = new JButton("Allocate");       	
        	panelButtons.add(buttonAllocate);
        	buttonAllocate.addActionListener(new ActionListener() {
    			
        		public void actionPerformed(ActionEvent e) {
        			allocateInventoryToSalesContract();

    			}
    		});
        	//
        	
        	
        panelButtons.setVisible(true);
        frame.add(panelButtons);
        
        //
	    
	    
	}

	// Convert Date to String
	public String convertDateToString(Date inDate){
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
		try{
			dateString = sdfr.format(inDate);
		}catch (Exception ex){
			System.out.println(ex);
		}
		
		return dateString;
	}
	
	// method to update InventoryJTable with the sales contract id after allocating a lot.
	
	public void allocateInventoryToSalesContract(){
		System.out.println("The Allocate Button is clicked");
		InventorySQLTable.allocateLotToSales();
		clearAllocateToContractForm();
		InventoryJTable.btnLoadInventory.doClick();
		
		
	}
	
	public void clearAllocateToContractForm(){
		lotNumberTextField.setText("");
		productStoredTextField.setText("");
		quantityStoredTextField.setText("");
		entryDateTextField.setText("");
		storageLocationTextField.setText("");
		salesContractForAllocationTextField.setText("");
	}
	
	//
	
	
}

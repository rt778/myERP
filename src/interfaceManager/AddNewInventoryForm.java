package interfaceManager;


import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inventoryManager.Inventory;
import inventoryManager.InventorySQLTable;

public class AddNewInventoryForm {

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
	
	private JPanel panelStorageLocation;
	private JLabel labelStorageLocation;
	private JTextField storageLocationTextField;
	
	private JPanel panelButtons;
	private JButton buttonCancel;
	private JButton buttonAdd;
	private JLabel labelAlign;

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewInventoryForm window = new AddNewInventoryForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddNewInventoryForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// The main frame
		frame = new JFrame("Add Inventory");
		frame.setBounds(100, 100, 450, 300);
		frame.setResizable(false);
		frame.setLayout(new GridLayout(6,1));
		frame.setLocationRelativeTo(buttonAdd);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		
		// The panel Lot Number
		panelLotNumber = new JPanel();
		
		labelLotNumber = new JLabel("       Lot Number:", JLabel.RIGHT);
		panelLotNumber.add(labelLotNumber);
		
		lotNumberTextField = new JTextField(17);
		panelLotNumber.add(lotNumberTextField);
		
		
		panelLotNumber.setVisible(true);
		frame.add(panelLotNumber);
		//
		
		
		
		//The Panel Product Stored
	    panelProductStored = new JPanel();
	    
	    labelProductStored = new JLabel("   Product Stored:", JLabel.RIGHT);
	    panelProductStored.add(labelProductStored);
	    panelProductStored.setVisible(true);
	    
	    productStoredTextField = new JTextField(17);
	    panelProductStored.add(productStoredTextField);
	    frame.add(panelProductStored);
	    //
		
		
	    // The quantity stored Panel
	    panelQuantityStored = new JPanel();
	    
	    
	    labelQuantityStored = new JLabel("  Quantity Stored:", JLabel.RIGHT);
	    panelQuantityStored.add(labelQuantityStored);
	    panelQuantityStored.setVisible(true);
	    
	    quantityStoredTextField = new JTextField(17);
	    panelQuantityStored.add(quantityStoredTextField);
	    frame.add(panelQuantityStored);
	    //
	    
	    
		
		//The Panel Entry Date
		panelEntryDate = new JPanel();
			
		labelEntryDate = new JLabel("       Entry Date:",JLabel.RIGHT);
		panelEntryDate.add(labelEntryDate);
		
		DatePicker d1 = new DatePicker(); 
        panelEntryDate.add(d1.showDatePicker()); 
        
	    
        panelEntryDate.setVisible(true);
        frame.add(panelEntryDate);
	    //
	    
        // The storageLocation Panel
	    panelStorageLocation = new JPanel();
	    
	    labelStorageLocation = new JLabel("Storage Location:", JLabel.RIGHT);
	    panelStorageLocation.add(labelStorageLocation);
	    panelStorageLocation.setVisible(true);
	    
	    storageLocationTextField = new JTextField(17);
	    panelStorageLocation.add(storageLocationTextField);
	    frame.add(panelStorageLocation);
	    //
        
        
        
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
        	
        	
        	// The Add Button
        	buttonAdd = new JButton("Add");       	
        	panelButtons.add(buttonAdd);
        	buttonAdd.addActionListener(new ActionListener() {
    			
        		public void actionPerformed(ActionEvent e) {
    				System.out.println("Add Button clicked");
    				
    				String valueLotNumber = lotNumberTextField.getText();
    				System.out.println(valueLotNumber);
    				
    				String valueProductStored = productStoredTextField.getText();
    				System.out.println(valueProductStored);
    				
    				Double valueQuantityStored = Double.parseDouble(quantityStoredTextField.getText());
    				System.out.println(valueQuantityStored);
    				
    				String valueEntryDate = DatePicker.selectedDate;
					System.out.println(valueEntryDate);
					
					String valueStorageLocation = storageLocationTextField.getText();
					System.out.println(valueStorageLocation);
					
					try {
						InventorySQLTable inventoryTable = new InventorySQLTable();
						inventoryTable.createInventorySQLTable();

						Inventory inv2 = new Inventory();

						inv2.setLotNumber(valueLotNumber);
						inv2.setProductStored(valueProductStored);
						inv2.setQuantityStored(valueQuantityStored);
						inv2.setEntryDate(valueEntryDate);
						inv2.setStorageLocation(valueStorageLocation);

						inventoryTable.addInventoryToSQLTable(inv2);
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					// in order to clear the text after the ADD button is clicked
					lotNumberTextField.setText("");
					productStoredTextField.setText("");
					quantityStoredTextField.setText("");
					storageLocationTextField.setText("");
					
					InventoryJTable.btnLoadInventory.doClick();
					
					


    			}
    		});
        	//
        	
        	
        panelButtons.setVisible(true);
        frame.add(panelButtons);
        
        //
	    
	    
	}

}

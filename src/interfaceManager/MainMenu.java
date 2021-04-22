package interfaceManager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 45, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		JButton btnLaunchSales = new JButton("Launch Sales");
		btnLaunchSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesContractsJTable.main(null);
			}
		});
		btnLaunchSales.setBounds(127, 69, 180, 29);
		frame.getContentPane().add(btnLaunchSales);
		
		JButton btnLaunchInventory = new JButton("Launch Inventory");
		btnLaunchInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryJTable.main(null);
			}
		});
		btnLaunchInventory.setBounds(127, 124, 180, 29);
		frame.getContentPane().add(btnLaunchInventory);
		
		JLabel lblTitleMainMenu = new JLabel("MyERP V1 Main Menu");
		lblTitleMainMenu.setBounds(145, 18, 162, 16);
		frame.getContentPane().add(lblTitleMainMenu);
	}
}

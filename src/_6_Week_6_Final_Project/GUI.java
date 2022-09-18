package _6_Week_6_Final_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GUI {
	ImageIcon logo;
	JFrame frame;
	JPanel mainMenuPanel;
	
	JPanel mainMenuPanel() {
		JPanel mainMenu = new JPanel();
		
		JPanel logoPanel = new JPanel();
		logoPanel.setPreferredSize(new Dimension(400, 100));
		JLabel logoAccount = new JLabel();
		logoAccount.setIcon(new ImageIcon(logo.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		logoAccount.setPreferredSize(new Dimension(100, 100));

		JPanel balanceSubPanel = new JPanel();
		balanceSubPanel.setLayout(new BorderLayout());
		balanceSubPanel.setPreferredSize(new Dimension(400, 40));
		balanceSubPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		JLabel balanceLabel = new JLabel("Balance: P0.00");
		balanceLabel.setFont(new Font(balanceLabel.getFont().getName(), 20, 20));
		JButton cashInButton = new JButton("Cash In");

		JPanel lifestyleServicesSubPanel = new JPanel();
		lifestyleServicesSubPanel.setPreferredSize(new Dimension(200, 200));
		lifestyleServicesSubPanel.setLayout(new GridLayout(0, 1));
		JLabel lifestyleServicesLabel = new JLabel("Lifestyle Services");
		JButton gamesServiceButton = new JButton("Games");
		JButton moviesServiceButton = new JButton("Movies");
		JButton sendMoneyServiceButton = new JButton("Send Money/Gift");
		JButton payBillsServiceButton = new JButton("Pay Bills");
		
		JPanel financialServiceSubPanel = new JPanel();
		financialServiceSubPanel.setLayout(new GridLayout(0, 1));
		financialServiceSubPanel.setPreferredSize(new Dimension(200, 200));
		JLabel financialServicesLabel = new JLabel("Financial Services");
		JButton fundTransferServiceButton = new JButton("Fund Transfer");
		JButton insuranceServiceButton = new JButton("Insurance");
		JButton loanServiceButton = new JButton("Loan");
		loanServiceButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Loan.mFrame();
				
			}
		});
		{
			
		}
		JButton purchaseServiceButton = new JButton("Purchase Product/Service");

		JLabel transactionHistoryLabel = new JLabel("Transaction History");
		transactionHistoryLabel.setPreferredSize(new Dimension(400, 15));
		JScrollPane transactionHistoryPane = new JScrollPane();
		transactionHistoryPane.setPreferredSize(new Dimension(400, 250));
		
		JButton logOutButton = new JButton("Log out");
		logOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				mainMenuPanel = null;
				frame.setLayout(null);
				frame.add(newLogin());
				frame.revalidate();
				frame.repaint();
			}
		});
		
		
		logoPanel.add(logoAccount);

		balanceSubPanel.add(balanceLabel, BorderLayout.WEST);
		balanceSubPanel.add(cashInButton, BorderLayout.EAST);
		
		lifestyleServicesSubPanel.add(lifestyleServicesLabel);
		lifestyleServicesSubPanel.add(gamesServiceButton);
		lifestyleServicesSubPanel.add(moviesServiceButton);
		lifestyleServicesSubPanel.add(sendMoneyServiceButton);
		lifestyleServicesSubPanel.add(payBillsServiceButton);
		
		financialServiceSubPanel.add(financialServicesLabel);
		financialServiceSubPanel.add(financialServicesLabel);
		financialServiceSubPanel.add(fundTransferServiceButton);
		financialServiceSubPanel.add(insuranceServiceButton);
		financialServiceSubPanel.add(loanServiceButton);
		financialServiceSubPanel.add(purchaseServiceButton);
		
		
		mainMenu.add(logoPanel);
		mainMenu.add(balanceSubPanel);
		mainMenu.add(lifestyleServicesSubPanel);
		mainMenu.add(financialServiceSubPanel);
		mainMenu.add(transactionHistoryLabel);
		mainMenu.add(transactionHistoryPane);
		mainMenu.add(logOutButton);
		
		return mainMenu;
	}
	
	JPanel newLogin() {
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(85, 180, 300, 250);
		loginPanel.setLayout(null);
		
		JLabel loginLogo = new JLabel();
		loginLogo.setIcon(new ImageIcon(logo.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		loginLogo.setBounds(110, 10, 100, 100);

		JLabel phoneNumberLabel = new JLabel("Phone Number:");
		phoneNumberLabel.setBounds(10, 120, 100, 25);
		
		JTextField phoneNumberField = new JTextField(20);
		phoneNumberField.setBounds(125, 120, 165, 25);
		
		JLabel pinCodeLabel = new JLabel("PIN Code:");
		pinCodeLabel.setBounds(10, 150, 80, 25);
		
		JPasswordField pinCodeField = new JPasswordField(20);
		pinCodeField.setBounds(125, 150, 165, 25);

		JLabel loginStatusLabel = new JLabel("");
		loginStatusLabel.setBounds(75, 210, 300, 25);

		JButton registerButton = new JButton("Register");
		registerButton.setBounds(50, 180, 85, 25);

		JButton loginButton = new JButton("Log in");
		loginButton.setBounds(150, 180, 80, 25);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String phoneNumber = phoneNumberField.getText();
				String pinCode = pinCodeField.getText();
				if (phoneNumber.equals("09454748745") && pinCode.equals("6969")) {
					loginStatusLabel.setText("");
					frame.setLayout(new BorderLayout());
					frame.getContentPane().removeAll();
					mainMenuPanel = mainMenuPanel();
					frame.add(mainMenuPanel);
					frame.revalidate();
					frame.repaint();
				} else {
					loginStatusLabel.setText("Credentials are not correct");
					loginStatusLabel.setForeground(Color.red);
				}
				loginPanel.revalidate();
				loginPanel.updateUI();
			}
		});

		loginPanel.add(loginLogo);
		loginPanel.add(phoneNumberLabel);
		loginPanel.add(phoneNumberField);
		loginPanel.add(pinCodeLabel);
		loginPanel.add(pinCodeField);
		loginPanel.add(loginStatusLabel);
		loginPanel.add(registerButton);
		loginPanel.add(loginButton);
		loginPanel.updateUI();
		return loginPanel;
	}
	
	public GUI() {
		frame = new JFrame();
		frame.setTitle("DigiCash");
		frame.setSize(480,720);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logo = new ImageIcon("assets/images/logo.png");		
		frame.setIconImage(logo.getImage());
		frame.setLayout(null);
		
		frame.add(newLogin());
		frame.revalidate();
		frame.repaint();
	}
}
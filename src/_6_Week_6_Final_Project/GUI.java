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
	Account currentUser = null;
	
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
		JLabel balanceLabel = new JLabel("Balance: P" + currentUser.balance);
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
				Loan.Loans();
				
			}
		});
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
				currentUser = null;
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
	 JPanel registrationPanel() {
		 JPanel registration = new JPanel();
		 registration.setBounds(0, 0 , 480, 720);
		 registration.setLayout(null);

		 JLabel registrationLogo = new JLabel();
		 registrationLogo.setIcon(logo);
		 registrationLogo.setBounds(75, 10, 317, 317);

		 JLabel phoneNumberLabel = new JLabel("Phone Number:");
		 phoneNumberLabel.setBounds(50, 310, 100, 80);
		 JTextField phoneNumberField = new JTextField(20);
		 phoneNumberField.setBounds(230, 338, 165, 25);
		 JLabel phoneNumberStatus = new JLabel();
		 phoneNumberStatus.setForeground(Color.RED);
		 phoneNumberStatus.setBounds(50, 330, 135, 80);

		 JLabel pinCodeLabel = new JLabel("Pin Code:");
		 pinCodeLabel.setBounds(50, 352, 100, 80);
		 JPasswordField pinCodeField = new JPasswordField(20);
		 pinCodeField.setBounds(230, 380, 165, 25);
		 JLabel pinCodeStatus = new JLabel();
		 pinCodeStatus.setForeground(Color.RED);
		 pinCodeStatus.setBounds(50, 372, 160, 80);

		 JLabel emailAddressLabel = new JLabel("Email Address:");
		 emailAddressLabel.setBounds(50, 393, 100, 80);
		 JTextField emailAddressField = new JTextField(20);
		 emailAddressField.setBounds(230, 420, 165, 25);
		 JLabel emailAddressStatus = new JLabel();
		 emailAddressStatus.setForeground(Color.RED);
		 emailAddressStatus.setBounds(50, 413, 100, 80);

		 JLabel firstNameLabel = new JLabel("First name:");
		 firstNameLabel.setBounds(50, 434, 100, 80);
		 JTextField firstNameField = new JTextField(20);
		 firstNameField.setBounds(230, 460, 165, 25);
		 JLabel firstNameStatus = new JLabel();
		 firstNameStatus.setForeground(Color.RED);
		 firstNameStatus.setBounds(50, 454, 100, 80);

		 JLabel lastNameLabel = new JLabel("Last name:");
		 lastNameLabel.setBounds(50, 474, 100, 80);
		 JLabel lastNameStatus = new JLabel();
		 lastNameStatus.setForeground(Color.RED);
		 lastNameStatus.setBounds(50, 494, 100, 80);
		 JTextField lastNameField = new JTextField(20);
		 lastNameField.setBounds(230, 500, 165, 25);


		 JButton backButton = new JButton("Back");
		 backButton.setBounds(130, 600, 80, 25);
		 backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.setLayout(null);
				frame.add(newLogin());
				frame.revalidate();
				frame.repaint();
			}
		});

		 JButton registerButton = new JButton("Register");
		 registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String phoneNumber = phoneNumberField.getText();
				if (phoneNumber.equals("")) {
					phoneNumberStatus.setText("Field is empty");
				} else if (phoneNumber.substring(0, 2).equals("09") && phoneNumber.length() == 11) {
					phoneNumberStatus.setText("");
				} else if (phoneNumber.substring(0, 4).equals("+639") && phoneNumber.length() == 13) {
					phoneNumberStatus.setText("");
				} else if (phoneNumber.substring(0, 1).equals("9") && phoneNumber.length() == 10) {
					phoneNumberStatus.setText("");
				} else {
					phoneNumberStatus.setText("Invalid phone number");
				}
				
				String pinCode = pinCodeField.getText();
				if (pinCode.equals("")) {
					pinCodeStatus.setText("Field is empty");
				} else if (pinCode.length() == 4) {
					pinCodeStatus.setText("");
				} else {
					pinCodeStatus.setText("Pin Code must be 4 digits");
				}
				
				String emailAddress = emailAddressField.getText();
				if (emailAddress.equals("")) {
					emailAddressStatus.setText("Field is empty");
				} else if (emailAddress.contains("@")) {
					emailAddressStatus.setText("");
				} else {
					emailAddressStatus.setText("Invalid email address");
				}
				
				String firstName = firstNameField.getText();
				if (firstName.equals("")) {
					firstNameStatus.setText("Field is empty");
				} else {
					firstNameStatus.setText("");
				}
				
				String lastName = lastNameField.getText();
				if (firstName.equals("")) {
					lastNameStatus.setText("Field is empty");
				} else {
					lastNameStatus.setText("");
				}
				
			}
		});
		 registerButton.setBounds(225, 600, 100, 25);
		 
		 registration.add(registrationLogo);
		 registration.add(phoneNumberLabel);
		 registration.add(phoneNumberField);
		 registration.add(phoneNumberStatus);
		 registration.add(pinCodeLabel);
		 registration.add(pinCodeField);
		 registration.add(pinCodeStatus);
		 registration.add(emailAddressLabel);
		 registration.add(emailAddressField);
		 registration.add(emailAddressStatus);
		 registration.add(firstNameLabel);
		 registration.add(firstNameField);
		 registration.add(firstNameStatus);
		 registration.add(lastNameLabel);
		 registration.add(lastNameField);
		 registration.add(lastNameStatus);
		 registration.add(backButton);
		 registration.add(registerButton);
		 registration.updateUI();
		 return registration;
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
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.add(registrationPanel());
				frame.revalidate();
				frame.repaint();
			}
		});
		registerButton.setBounds(50, 180, 85, 25);

		JButton loginButton = new JButton("Log in");
		loginButton.setBounds(150, 180, 80, 25);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String phoneNumberInput = phoneNumberField.getText();
				String pinCodeInput = pinCodeField.getText();
				
				if (!phoneNumberInput.equals("") && phoneNumberInput.substring(0, 1).equals("0") && phoneNumberInput.length() == 11) {
					phoneNumberInput = phoneNumberInput.substring(1, 11);
				} else if (!phoneNumberInput.equals("") && phoneNumberInput.substring(0, 3).equals("+63") && phoneNumberInput.length() == 13) {
					phoneNumberInput = phoneNumberInput.substring(3, 13);
				}
					
				currentUser = new Account(phoneNumberInput, pinCodeInput);
				if (!currentUser.isConnected) {
					currentUser = null;
				}
				if (currentUser == null) {
					loginStatusLabel.setText("No connection");
					loginStatusLabel.setForeground(Color.red);
				} else if (currentUser.loginSuccess) {
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
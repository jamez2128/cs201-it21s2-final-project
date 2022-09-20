package _6_Week_6_Final_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.util.Date;

public class GUI {
	ImageIcon logo;
	JFrame frame;
	JPanel mainMenuPanel;
	static Account currentUser = null;
	
	public static boolean paymentPortal(double amount) {
		int option = JOptionPane.showConfirmDialog(null, "You are about to pay P" + String.format("%,.2f", amount) + "\nClick Yes confirm to continue the transaction\nOtherwise click No", "Payment Portal", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		switch (option) {
		case -1:
		case 1:
			return false;
		case 0:
			JOptionPane.showMessageDialog(null, "Paid P" + String.format("%,.2f", amount) + " successfully\nThank you for using DigiCash!", null, JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		return false;
	}
	
	public static boolean isBalanceSufficient(double amount) {
		currentUser.updateInfo();
		if (currentUser.balance < amount) {
			JOptionPane.showMessageDialog(null, "Insufficient balance", null, JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean askPINCode() {
		JPasswordField pinCodeField = new JPasswordField();
		Object[] pinCodeObjects = {"Enter PIN Code:", pinCodeField};
		int pinCodeInput = JOptionPane.showConfirmDialog(null, pinCodeObjects, "Enter PIN code:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
		switch (pinCodeInput) {
		case -1:
			JOptionPane.showMessageDialog(null, "Operation cancelled", null, JOptionPane.WARNING_MESSAGE);
			return false;
		case 0:
			if (currentUser.pinCode.equals(pinCodeField.getText())) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Wrong PIN code!\nOperation cancelled", null, JOptionPane.WARNING_MESSAGE);
				return false;
			}
		case 2:
			JOptionPane.showMessageDialog(null, "Operation cancelled", null, JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return false;
	}
	
	JButton transactionInfoButton(double amount, String description, Date date) {
		JButton infoButton = new JButton();
		infoButton.setLayout(new BorderLayout(10, 0));
		infoButton.setPreferredSize(new Dimension(375, 60));
		infoButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		JLabel amountLabel = new JLabel();
		if (amount == 0) {
			amountLabel.setText("");
		} else if (amount < 0) {
			amount = amount * (-1);
			amountLabel.setText("- P" + String.format("%,.2f", amount));
			amountLabel.setForeground(Color.red);
		} else {
			amountLabel.setText("+ P" + String.format("%,.2f", amount));
			amountLabel.setForeground(new Color(0, 215, 0));
		}
		JLabel descriptionLabel = new JLabel(description);
		JLabel dateLabel = new JLabel(new SimpleDateFormat("MM/dd/yyyy h:mm a").format(date));
		
		infoButton.add(amountLabel, BorderLayout.WEST);
		infoButton.add(descriptionLabel, BorderLayout.CENTER);
		infoButton.add(dateLabel, BorderLayout.EAST);
		infoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, ""
						+ "Amount: " + amountLabel.getText() + "\n"
						+ "Description: " + description + "\n"
						+ "Date: " + new SimpleDateFormat("MM/dd/yyyy h:mm a").format(date),
						"Transaction Info", JOptionPane.PLAIN_MESSAGE);
			}
		});
		return infoButton;
	}
	
	public void refreshMainMenu() {
		currentUser.updateInfo();
		frame.getContentPane().removeAll();;
		mainMenuPanel = mainMenuPanel();
		frame.add(mainMenuPanel());
		frame.revalidate();
		frame.repaint();
	}
	
	JPanel mainMenuPanel() {
		JPanel mainMenu = new JPanel();
		
		JPanel logoPanel = new JPanel();
		logoPanel.setPreferredSize(new Dimension(400, 100));
		JLabel logoAccount = new JLabel();
		logoAccount.setIcon(new ImageIcon(logo.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		logoAccount.setPreferredSize(new Dimension(100, 100));
		JLabel welcomeLabel = new JLabel("Welcome " + currentUser.firstName + "!");
		welcomeLabel.setFont(new Font(welcomeLabel.getFont().getName(), 20, 20));

		JPanel balanceSubPanel = new JPanel();
		balanceSubPanel.setLayout(new BorderLayout());
		balanceSubPanel.setPreferredSize(new Dimension(400, 40));
		balanceSubPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		JLabel balanceLabel = new JLabel("Balance: P" + String.format("%,.2f", currentUser.balance));
		balanceLabel.setFont(new Font(balanceLabel.getFont().getName(), 20, 20));
		JButton cashInButton = new JButton("Cash In");
		cashInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cashInput = JOptionPane.showInputDialog("How much will you cash in?");
				if (cashInput == null || cashInput.isEmpty()) {
					return;
				} else if (cashInput.matches("\\D+")) {
					JOptionPane.showMessageDialog(null, "Invalid input, Operation cancelled");
					return;
				} else {
					currentUser.updateInfo();
					double cashInputParse = Double.parseDouble(cashInput);
					currentUser.cashIn(cashInputParse, "Cash In");
					refreshMainMenu();
				}
				mainMenuPanel.updateUI();
			}
		});

		JPanel lifestyleServicesSubPanel = new JPanel();
		lifestyleServicesSubPanel.setPreferredSize(new Dimension(200, 200));
		lifestyleServicesSubPanel.setLayout(new GridLayout(0, 1));
		JLabel lifestyleServicesLabel = new JLabel("Lifestyle Services");
		JButton gamesServiceButton = new JButton("Games");
		gamesServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (LifestyleServices.Games()) {
					refreshMainMenu();
				}
			}
		});
		JButton moviesServiceButton = new JButton("Movies");
		moviesServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (LifestyleServices.movies()) {
					refreshMainMenu();
				}
			}
		});
		JButton sendMoneyServiceButton = new JButton("Send Money/Gift");
		sendMoneyServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (LifestyleServices.sendMoneyGift()) {
					refreshMainMenu();
				}
			}
		});
		JButton payBillsServiceButton = new JButton("Pay Bills");
		payBillsServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (LifestyleServices.payBills()) {
					refreshMainMenu();
				}
			}
		});
		
		JPanel financialServiceSubPanel = new JPanel();
		financialServiceSubPanel.setLayout(new GridLayout(0, 1));
		financialServiceSubPanel.setPreferredSize(new Dimension(200, 200));
		JLabel financialServicesLabel = new JLabel("Financial Services");
		JButton fundTransferServiceButton = new JButton("Fund Transfer");
		fundTransferServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (FinancialServices.fundTransfer()) {
					refreshMainMenu();
				}
			}
		});
		JButton insuranceServiceButton = new JButton("Insurance");
		insuranceServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (FinancialServices.insurance()) {
					refreshMainMenu();
				}
			}
		});
		JButton loanServiceButton = new JButton("Loan");
		loanServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (FinancialServices.loan()) {
					currentUser.updateInfo();
					refreshMainMenu();
				}
			}
		});
		JButton purchaseServiceButton = new JButton("Purchase Product/Service");
		purchaseServiceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (FinancialServices.purchaseService()) {
					refreshMainMenu();
				}
			}
		});
		JButton creditDebitServiceButton = new JButton("Credit and Debit");

		JLabel transactionHistoryLabel = new JLabel("Transaction History");
		transactionHistoryLabel.setPreferredSize(new Dimension(400, 15));
		JPanel transactionHistoryPanel = new JPanel();
		transactionHistoryPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		transactionHistoryPanel.setLayout(new BoxLayout(transactionHistoryPanel, BoxLayout.Y_AXIS));
		for (TransactionHistory element : currentUser.transactionHistoryList) {
			transactionHistoryPanel.add(transactionInfoButton(element.amount, element.description, element.date));
		}
		JScrollPane transactionHistoryPane = new JScrollPane(transactionHistoryPanel);
		transactionHistoryPane.setPreferredSize(new Dimension(400, 250));
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshMainMenu();
			}
		});
		
		JButton accountButton = new JButton("Account");
		accountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, ""
						+ "Name: " + currentUser.firstName + " " + currentUser.lastName + "\n"
						+ "Phone Number: +63" + currentUser.phoneNumber + "\n"
						+ "Email Address: " + currentUser.emailAddress);
			}
		});
		
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
		logoPanel.add(welcomeLabel);

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
		financialServiceSubPanel.add(creditDebitServiceButton);
		
		
		mainMenu.add(logoPanel);
		mainMenu.add(balanceSubPanel);
		mainMenu.add(lifestyleServicesSubPanel);
		mainMenu.add(financialServiceSubPanel);
		mainMenu.add(transactionHistoryLabel);
		mainMenu.add(transactionHistoryPane);
		mainMenu.add(refreshButton);
		mainMenu.add(accountButton);
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
		 phoneNumberStatus.setBounds(50, 330, 225, 80);

		 JLabel pinCodeLabel = new JLabel("Pin Code (4 digits):");
		 pinCodeLabel.setBounds(50, 352, 150, 80);
		 JPasswordField pinCodeField = new JPasswordField(20);
		 pinCodeField.setBounds(230, 380, 165, 25);
		 JLabel pinCodeStatus = new JLabel();
		 pinCodeStatus.setForeground(Color.RED);
		 pinCodeStatus.setBounds(50, 372, 200, 80);

		 JLabel emailAddressLabel = new JLabel("Email Address:");
		 emailAddressLabel.setBounds(50, 393, 100, 80);
		 JTextField emailAddressField = new JTextField(20);
		 emailAddressField.setBounds(230, 420, 165, 25);
		 JLabel emailAddressStatus = new JLabel();
		 emailAddressStatus.setForeground(Color.RED);
		 emailAddressStatus.setBounds(50, 413, 150, 80);

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

		 JLabel registrationStatus = new JLabel("");
		 registrationStatus.setBounds(205, 525, 150, 80);
		 if (Account.isConnected == false) {
			 registrationStatus.setForeground(Color.RED);
			 registrationStatus.setText("No connection");
		 }

		 JButton registerButton = new JButton("Register");
		 registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int checks = 0;
				String phoneNumber = phoneNumberField.getText();
				String pinCode = pinCodeField.getText();
				String emailAddress = emailAddressField.getText();
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();

				if (phoneNumber.equals("")) {
					phoneNumberStatus.setText("Field is empty");
				} else if (phoneNumber.matches("\\D+")) {
					phoneNumberStatus.setText("Invalid phone number");
				} else if (phoneNumber.substring(0, 2).equals("09") && phoneNumber.length() == 11) {
					phoneNumberStatus.setText("");
					phoneNumber = phoneNumber.substring(1, 11);
					checks += 1;
				} else if (phoneNumber.substring(0, 4).equals("+639") && phoneNumber.length() == 13) {
					phoneNumberStatus.setText("");
					phoneNumber = phoneNumber.substring(3, 13);
					checks += 1;
				} else if (phoneNumber.substring(0, 1).equals("9") && phoneNumber.length() == 10) {
					phoneNumberStatus.setText("");
					checks += 1;
				} else {
					phoneNumberStatus.setText("Invalid phone number");
				}
				
				if (pinCode.equals("")) {
					pinCodeStatus.setText("Field is empty");
				} else if (pinCode.matches("\\D+")) {
					pinCodeStatus.setText("Must not contain characters");
				} else if (pinCode.length() == 4) {
					pinCodeStatus.setText("");
					checks += 1;
				} else {
					pinCodeStatus.setText("Pin Code must be 4 digits");
				}
				
				if (emailAddress.equals("")) {
					emailAddressStatus.setText("Field is empty");
				} else if (emailAddress.length() >= 255) {
					firstNameStatus.setText("Exceeded character limit");
				} else if (emailAddress.contains("@")) {
					emailAddressStatus.setText("");
					checks += 1;
				} else {
					emailAddressStatus.setText("Invalid email address");
				}
				
				if (firstName.equals("")) {
					firstNameStatus.setText("Field is empty");
				} else if (firstName.length() >= 255) {
					firstNameStatus.setText("Exceeded character limit");
				} else {
					firstNameStatus.setText("");
					checks += 1;
				}
				
				if (lastName.equals("")) {
					lastNameStatus.setText("Field is empty");
				} else if (lastName.length() >= 255) {
					lastNameStatus.setText("Exceeded character limit");
				} else {
					lastNameStatus.setText("");
					checks += 1;
				}
				
				if (checks == 5) {
					if (Account.isAccountExists(phoneNumber) == false) {
						Account.register(phoneNumber, pinCode, firstName, lastName, emailAddress);
						phoneNumberField.setText("");
						pinCodeField.setText("");
						emailAddressField.setText("");
						firstNameField.setText("");
						lastNameField.setText("");
						registrationStatus.setForeground(new Color(0, 215, 0));
						registrationStatus.setText("Registered Successfully!");
					} else {
						phoneNumberStatus.setText("Phone number is already registered");
					}
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
		 registration.add(registrationStatus);
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
		if (Account.isConnected == false) {
			loginStatusLabel.setForeground(Color.RED);
			loginStatusLabel.setText("No Connection");
		}

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
				if (Account.isConnected == false) {
					currentUser = null;
				}
				if (currentUser.loginSuccess) {
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
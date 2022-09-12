package _6_Week_6_Final_Project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI {
	JFrame frame;
	JPanel mainMenuPanel;
	
	JPanel mainMenuPanel() {
		JPanel mainMenuPanel = new JPanel();
		
		JPanel servicesSubPanel = new JPanel();
		servicesSubPanel.setPreferredSize(new Dimension(360, 500));
		JPanel balanceSubPanel = new JPanel();
		JLabel balanceLabel = new JLabel("Balance: P0.00");
		JButton cashInButton = new JButton("Cash In");

		JPanel lifestyleServicesSubPanel = new JPanel();
		JLabel lifestyleServicesLabel = new JLabel("Lifestyle Services");
		JButton gamesServiceButton = new JButton("Games");
		JButton moviesServiceButton = new JButton("Movies");
		JButton sendMoneyServiceButton = new JButton("Send Money/Gift");
		JButton payBillsServiceButton = new JButton("Pay Bills");
		
		JPanel financialServiceSubPanel = new JPanel();
		JLabel financialServicesLabel = new JLabel("Financial Services");
		JButton fundTransferServiceButton = new JButton("Fund Transfer");
		JButton insuranceServiceButton = new JButton("Insurance");
		JButton loanServiceButton = new JButton("Loan");
		JButton purchaseServiceButton = new JButton("Purchase Product/Service");
		
		
		JButton logOutButton = new JButton("Log out");

		logOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.add(newLogin());
				frame.revalidate();
				frame.repaint();
			}
		});
		balanceSubPanel.add(balanceLabel);
		balanceSubPanel.add(cashInButton);
		
		lifestyleServicesSubPanel.add(lifestyleServicesLabel);
		lifestyleServicesSubPanel.add(gamesServiceButton);
		lifestyleServicesSubPanel.add(moviesServiceButton);
		lifestyleServicesSubPanel.add(sendMoneyServiceButton);
		lifestyleServicesSubPanel.add(payBillsServiceButton);
		
		financialServiceSubPanel.add(financialServicesLabel);
		financialServiceSubPanel.add(fundTransferServiceButton);
		financialServiceSubPanel.add(insuranceServiceButton);
		financialServiceSubPanel.add(loanServiceButton);
		financialServiceSubPanel.add(purchaseServiceButton);
		
		servicesSubPanel.add(lifestyleServicesSubPanel);
		servicesSubPanel.add(financialServiceSubPanel);
		
		mainMenuPanel.add(balanceSubPanel);
		mainMenuPanel.add(servicesSubPanel);
		mainMenuPanel.add(logOutButton);
		
		return mainMenuPanel;
	}
	
	JPanel newLogin() {
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);
		
		JLabel phoneNumberLabel = new JLabel("Phone Number:");
		phoneNumberLabel.setBounds(10, 20, 100, 25);
		
		JTextField phoneNumberField = new JTextField(20);
		phoneNumberField.setBounds(125, 20, 165, 25);
		
		JLabel pinCodeLabel = new JLabel("PIN Code:");
		pinCodeLabel.setBounds(10, 50, 80, 25);
		
		JPasswordField pinCodeField = new JPasswordField(20);
		pinCodeField.setBounds(125, 50, 165, 25);

		JLabel loginStatusLabel = new JLabel("");
		loginStatusLabel.setBounds(10, 110, 300, 25);

		JButton loginButton = new JButton("Log in");
		loginButton.setBounds(10, 80, 80, 25);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String phoneNumber = phoneNumberField.getText();
				String pinCode = pinCodeField.getText();
				if (phoneNumber.equals("09454748745") && pinCode.equals("6969")) {
					loginStatusLabel.setText("");
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

		loginPanel.add(phoneNumberField);
		loginPanel.add(phoneNumberLabel);
		loginPanel.add(pinCodeLabel);
		loginPanel.add(pinCodeField);
		loginPanel.add(loginStatusLabel);
		loginPanel.add(loginButton);
		loginPanel.updateUI();
		return loginPanel;
	}
	
	public GUI() {
			
		frame = new JFrame();
		frame.setTitle("DigiCash");
		frame.setSize(480,720);
		frame.setVisible(true);
//		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon("assets/images/logo.png");
		frame.setIconImage(logo.getImage());
		
		frame.add(newLogin());
		frame.revalidate();
		frame.repaint();
	}
}
package _6_Week_6_Final_Project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {
	JFrame frame;
	JPanel mainMenuPanel;
	Login login;
	
	JPanel mainMenuPanel() {
		JPanel mainMenuPanel = new JPanel();
		
		JPanel servicesSubPanel = new JPanel();
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
		login = new Login();
		JPanel loginPanel = login.loginPanel(new Consumer<Integer>() {
			@Override
			public void accept(Integer t) {
				if (login.phoneNumber.equals("09454748745") && login.pinCode.equals("6969")) {
					login.loginStatusLabel.setText("");
					frame.getContentPane().removeAll();
					frame.add(mainMenuPanel);
					frame.revalidate();
					frame.repaint();
				} else {
					login.loginStatusLabel.setText("Credentials are not correct");
					login.loginStatusLabel.setForeground(Color.red);
				}
			}
		});
		return loginPanel;
	}
	
	public GUI() {
			
		frame = new JFrame();
		frame.setTitle("DigiCash");
		frame.setSize(500,750);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon("assets/images/logo.png");
		frame.setIconImage(logo.getImage());
		
		mainMenuPanel = mainMenuPanel();
		
		frame.add(newLogin());
		frame.revalidate();
		frame.repaint();
	}
}
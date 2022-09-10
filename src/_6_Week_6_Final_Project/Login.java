package _6_Week_6_Final_Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	String phoneNumber;
	String pinCode;
	JLabel loginStatusLabel;
	
	JPanel loginPanel(Consumer<Integer> action) {
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

		loginStatusLabel = new JLabel("");
		loginStatusLabel.setBounds(10, 110, 300, 25);

		JButton loginButton = new JButton("Log in");
		loginButton.setBounds(10, 80, 80, 25);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				phoneNumber = phoneNumberField.getText();
				pinCode = pinCodeField.getText();
				action.accept(null);
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

}

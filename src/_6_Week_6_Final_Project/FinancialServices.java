package _6_Week_6_Final_Project;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FinancialServices {

	public static boolean FundTransfer() {
		JTextField bankNameField = new JTextField();
		JTextField amountField = new JTextField();
		JTextField accountNumberField = new JTextField();
		
		Object[] fields = {
				"Enter Bank:", bankNameField,
				"Enter Amount:", amountField,
				"Enter Account Number:", accountNumberField
		};
		
		int response = JOptionPane.showConfirmDialog(null, fields, "Fund Transfer!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
		switch (response) {
		case -1:
			break;
		case 0:
			String bankName = bankNameField.getText();
			double amount;
			String accountNumber = accountNumberField.getText();
			
			if (bankName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Bank name is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (accountNumber.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Account number is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			}

			if (amountField.getText().matches("\\D+")) {
				JOptionPane.showMessageDialog(null, "Invalid amount please try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				amount = Double.parseDouble(amountField.getText());
			}
			
			String pinCodeInput = JOptionPane.showInputDialog("Enter PIN Code:");
			if (!GUI.currentUser.pinCode.equals(pinCodeInput)) {
				JOptionPane.showMessageDialog(null, "Wrong PIN code!\nOperation cancelled", null, JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (GUI.currentUser.balance < amount) {
				JOptionPane.showMessageDialog(null, "Insufficient balance", null, JOptionPane.WARNING_MESSAGE);
			} else {
				double newAmount = GUI.currentUser.balance - amount;
				Account.changeBalance(GUI.currentUser.id, newAmount);
				Account.addToHistory(GUI.currentUser.id, bankName +  " bank transfer to " + accountNumber, (amount * (-1)));
				return true;
			}
			break;
		case 2:
			break;
		}
		return false;
	}
}

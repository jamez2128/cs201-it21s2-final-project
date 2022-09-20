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
			return false;
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
			
			if (GUI.askPINCode() == false) {
				return false;
			}
			
			if (GUI.isBalanceSufficient(amount) == false) {
				return false;
			}
			
			Account.transact(amount, bankName +  " bank transfer to " + accountNumber);
			return true;
		case 2:
			return false;
		}
		return false;
	}
}

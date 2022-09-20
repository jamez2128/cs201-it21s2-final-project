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
			String accountNumber = "";
			
			if (bankName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Bank name is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			}
			
			if (accountNumber.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Account number is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			}

			if (accountNumber.matches("\\D+")) {
				JOptionPane.showMessageDialog(null, "Invalid acccount number please try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				accountNumber = accountNumberField.getText();
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
			
			if (GUI.paymentPortal(amount) == false) {
				return false;
			}
			
			
			GUI.currentUser.transact(amount, bankName +  " bank transfer to " + accountNumber);
			return true;
		case 2:
			return false;
		}
		return false;
	}
	
	public static boolean Loan() {
		String value = JOptionPane.showInputDialog(null, "Enter the amount you want to loan:", "Loan", JOptionPane.QUESTION_MESSAGE);
		if (value == null) {
			return false;
		}
		double valueParsed = 0;
		if (value.isEmpty()) {
			JOptionPane.showMessageDialog(null, "The field is empty!", "Loan", JOptionPane.ERROR_MESSAGE);
		} else if (value.matches("\\D+")) {
			JOptionPane.showMessageDialog(null, "Invalid input", "Loan", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			valueParsed = Double.parseDouble(value);
		}
		GUI.currentUser.updateInfo();
		GUI.currentUser.cashIn(valueParsed, "Borrowed Loan");
		JOptionPane.showMessageDialog(null, "Your loan has been approved!", "Loan", JOptionPane.DEFAULT_OPTION);
		return true;
	}
}

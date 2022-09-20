package _6_Week_6_Final_Project;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FinancialServices {

	public static boolean fundTransfer() {
		JTextField bankNameField = new JTextField();
		JTextField amountField = new JTextField();
		JTextField accountNumberField = new JTextField();
		String bankName = bankNameField.getText();
		double amount = 0;
		String accountNumber = "";

		Object[] fields = {
				"Enter Bank:", bankNameField,
				"Enter Amount:", amountField,
				"Enter Account Number:", accountNumberField
		};
		
		int response = JOptionPane.showConfirmDialog(null, fields, "Fund Transfer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
		switch (response) {
		case -1:
		case 2:
			return false;
		case 0:
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
			break;
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
	}
	
	public static boolean loan() {
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
	
	public static boolean insurance() {
	    JTextField field1 = new JTextField();
	    JTextField field2 = new JTextField();
	    JTextField field3 = new JTextField();
	    String insuranceType = "";
	    double amount = 0;
	    String name = "";
	    
	    Object[] fields = {
	    	"Enter Type of Insurance", field1,
	        "Enter Amount", field2,
	        "Enter Name", field3
	    };

		int response = JOptionPane.showConfirmDialog(null, fields, "Insurance", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
		switch (response) {
		case -1:
		case 2:
			return false;
		case 0:
			if (field1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Insurance type is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				insuranceType = field1.getText();
			}
			
			if (field2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Amount field is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			}

			if (field2.getText().matches("\\D+")) {
				JOptionPane.showMessageDialog(null, "Invalid amount please try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				amount = Double.parseDouble(field2.getText());
			}
			
			if (field3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Name field is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				name = field3.getText();
			}
			break;
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
			
		GUI.currentUser.transact(amount, "Paid " + insuranceType + " insurance for " + name);
		
		return true;
	}
	
	public static boolean purchaseService() {
		JTextField field1 = new JTextField();
	    JTextField field2 = new JTextField();
	    JTextField field3 = new JTextField();
	    JTextField field4 = new JTextField();
	    String service = "";
	    double amount = 0;
	    String product = "";
	    String address = "";
	    
	    Object[] fields = {
	    	"Enter service", field1,
	        "Enter Amount", field2,
	        "Enter product:", field3,
	        "Enter Address:", field4
	    };

		int response = JOptionPane.showConfirmDialog(null, fields, "Purchase service", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
		switch (response) {
		case -1:
		case 2:
			return false;
		case 0:
			if (field1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Service field is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				service = field1.getText();
			}
			
			if (field2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Amount field is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			}

			if (field2.getText().matches("\\D+")) {
				JOptionPane.showMessageDialog(null, "Invalid amount please try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				amount = Double.parseDouble(field2.getText());
			}
			
			if (field3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Product field is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				product = field3.getText();
			}

			if (field4.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Address field is empty\nPlease try again", null, JOptionPane.WARNING_MESSAGE);
				return false;
			} else {
				address = field4.getText();
			}
			break;
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
			
		GUI.currentUser.transact(amount, "Bought " + product + " from " + service + " and will be delivered in " + address);
		
		return true;
	}
}
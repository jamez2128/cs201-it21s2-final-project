package _6_Week_6_Final_Project;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class Loan {
	
	public static void Loans() {
	
		int value;
		
		value = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the value:", "Loan", JOptionPane.QUESTION_MESSAGE));
		
		if (value == null)
			JOptionPane.showMessageDialog(null, "The field is empty!", "Loan", JOptionPane.ERROR_MESSAGE);
		else if(value == String)
			JOptionPane.showMessageDialog(null, "Invalid input", "Loan", JOptionPane.ERROR_MESSAGE);
		else
			balanceLabel = balanceLabel + value;
			JOptionPane.showMessageDialog(null, "Your loan is approved!" + "Your loan is to be payed at (30 days after your loan got approved.)", "Loan", JOptionPane.DEFAULT_OPTION);
	}

}

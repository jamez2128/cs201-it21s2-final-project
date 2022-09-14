package _6_Week_6_Final_Project;

import javax.swing.JOptionPane;
public class Loan {

	public static void Loans() {
		
		int num;
		
		num = Integer.parseInt(JOptionPane.showInputDialog(null, "How much Loan do you want?", "Loan", JOptionPane.PLAIN_MESSAGE));
		
		JOptionPane.showMessageDialog(null, "Your Loan that you borrow: " + num, "Loan", JOptionPane.PLAIN_MESSAGE);
	}

}

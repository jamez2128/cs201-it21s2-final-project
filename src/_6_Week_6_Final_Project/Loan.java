package _6_Week_6_Final_Project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Loan {
	
	static JPanel Loans() {
		JPanel panel = new JPanel();
			panel.setSize(400, 200);
			
		JLabel label = new JLabel("Please enter the value: ");
			label.setFont(new Font(label.getFont().getName(), 15, 15));
			label.setFont(new Font(label.getFont().getName(), Font.BOLD, 13));
			
		JTextField textbox = new JTextField(20);
			label.setBounds(20, 40, 0, 0);
		
		JButton enter = new JButton("Enter");
			enter.setBounds(25, 20, 0, 0);
			
		JButton cancel = new JButton("Cancel");
			cancel.setBounds(25, 20, 0, 0);
		
		
			
			panel.add(label);
			panel.add(textbox);
			panel.add(enter);
			panel.add(cancel);

		return panel;
	}
	
	static void Transaction() {
		
		int num;
		
		
	}
	
	public static void mFrame() {
		JFrame frame = new JFrame();	
		frame.setSize(400, 200);
		frame.setTitle("Loan");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.add(Loans());
		frame.setLayout(null);
	}

}

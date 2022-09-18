package _6_Week_6_Final_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Loan {
	
	static JPanel Loans() {
		JPanel panel = new JPanel();
			panel.setSize(300, 200);
			panel.setLayout(null);
			
		JLabel label = new JLabel("Please enter the value: ");
			label.setBounds(80,20,150,30);
			label.setFont(new Font(label.getFont().getName(), 18, 18));
			label.setFont(new Font(label.getFont().getName(), Font.BOLD, 13));
			
		JTextField textbox = new JTextField(20);
			textbox.setBounds(60, 50, 182, 25);
		
		JButton enter = new JButton("Enter");
			enter.setBounds(60, 80, 80, 25);
		
		JButton cancel = new JButton("Cancel");
			cancel.setBounds(161, 80, 80, 25);		

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
		frame.setSize(300, 200);
		frame.setTitle("Loan");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.add(Loans());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}

package _6_Week_6_Final_Project;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Loan {
	
	public static void Loans() {
		JFrame frame = new JFrame();
		ImageIcon logo = new ImageIcon("assets/images/logo.png");	
		frame.setSize(400, 300);
		frame.setName("Loan");
		frame.setVisible(true);
		frame.setResizable(false);
	}

}

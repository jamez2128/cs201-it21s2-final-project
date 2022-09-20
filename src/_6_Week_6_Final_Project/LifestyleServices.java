package _6_Week_6_Final_Project;

import javax.swing.JOptionPane;

public class LifestyleServices {

	public static boolean Movies() {
		double amount = 0;
		String service = "";
		
		String[] moviesMainMenu = {
				"Disney Plus",
				"Hulu",
				"Netflix"
		};

		double[] disneyPlusPrices = {
				445.51,
				625.54,
				4560
		};
		
		String[] disneyPlusChoices = {
			"P" + disneyPlusPrices[0] + " per month ad-supported version",
			"P" + disneyPlusPrices[1] + " per month ad free version",
			"P" + disneyPlusPrices[2] + " Get year-round access to disney"
		};
		
		double[] huluPrices = {
				3990,
				4332
		};
		
		String[] huluChoices = {
				"P" + huluPrices[0] + " Hulu + Live Tv",
				"P" + huluPrices[1] + " Hulu no Ad + Live Tv\n "
		};
		
		double[] netflixPrices = {
				569.53,
				883.08,
				1139
		};
		
		String[] netflixChoices = {
			"P" + netflixPrices[0] + " Per Month Plan offering 480P Streaming",
			"P" + netflixPrices[1] + "883.08-Per Month Plan for 1080P Streaming",
			"P" + netflixPrices[2] + "1,139-Per Month Plan for for 4k Streaming "
		};
		
		int choice = JOptionPane.showOptionDialog(null, "************Movie Monthly Payment**********", "Movies", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, moviesMainMenu, 0);
		
		switch (choice) {
		case -1:
			return false;
		case 0:
			int disneyPlusChoice = JOptionPane.showOptionDialog(null, "Disney+", "Movies", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, disneyPlusChoices, 0);
			service = "Disney+";
			switch (disneyPlusChoice) {
			case -1:
				return false;
			case 0:
				amount = disneyPlusPrices[0];
				break;
			case 1:
				amount = disneyPlusPrices[1];
				break;
			case 2:
				amount = disneyPlusPrices[2];
				break;
			}
			break;
		case 1:
			int huluChoice = JOptionPane.showOptionDialog(null, "Hulu", "Movies", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, huluChoices, 0);
			service = "Hulu";
			switch (huluChoice) {
			case -1:
				return false;
			case 0:
				amount = huluPrices[0];
				break;
			case 1:
				amount = huluPrices[1];
				break;
			case 2:
				amount = huluPrices[3];
				break;
			}
			break;
		case 2:
			int netflixChoice = JOptionPane.showOptionDialog(null, "Netflix", "Movies", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, netflixChoices, 0);
			service = "Netflix";
			switch (netflixChoice) {
			case -1:
				return false;
			case 0:
				amount = netflixPrices[0];
				break;
			case 1:
				amount = netflixPrices[1];
				break;
			case 2:
				amount = netflixPrices[2];
				break;
			}
			break;
		}

		if (GUI.askPINCode() == false) {
			return false;
		}
			
		if (GUI.isBalanceSufficient(amount) == false) {
			return false;
		}

		GUI.currentUser.transact(amount, service + " subscription service");
		return true;
	}
}
package _6_Week_6_Final_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class Account {
	int id;
	String phoneNumber;
	String pinCode;
	double balance;
	String lastName;
	String firstName;
	String emailAddress;
	boolean loginSuccess = false;
	boolean isConnected = false;
	
	static Connection localConn;

	public Account(String phoneNumberInput, String pinCodeInput) {
		try {
			if (localConn == null) {
				localConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/digicash", "mysqluser", "password");
			}
			isConnected = true;
			PreparedStatement loginStatement  = localConn.prepareStatement("select * from accounts where phoneNumber = ?");
			loginStatement.setString(1, phoneNumberInput);
			ResultSet loginResults = loginStatement.executeQuery();
			if (!loginResults.isBeforeFirst()) {
				loginSuccess = false;
				return;
			}
			while (loginResults.next()) {
				phoneNumber = loginResults.getString("phoneNumber");
				pinCode = loginResults.getString("pinCode");
				 if (pinCode.equals(pinCodeInput)) {
					 id = loginResults.getInt("id");
					 lastName = loginResults.getString("lastName");
					 firstName = loginResults.getString("firstName");
					 emailAddress = loginResults.getString("emailAddress");
					 balance = loginResults.getDouble("balance");
					 loginSuccess = true;
					 return;
				} else {
					loginSuccess = false;
					return;
				}
			}
		} catch (SQLException e) {
			isConnected = false;
			e.printStackTrace();
		}
	}
	
	public static void register(String phoneNumber, String pinCode, String firstName, String lastName, String emailAddress) {
		try {
			if (localConn == null) {
				localConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/digicash", "mysqluser", "password");
			}
			PreparedStatement registerStatment = localConn.prepareStatement("insert into accounts (phoneNumber, pinCode, firstName, lastName, emailAddress, balance) values (?, ?, ?, ?, ?, 0);");
			registerStatment.setString(1, phoneNumber);
			registerStatment.setString(2, pinCode);
			registerStatment.setString(3, firstName);
			registerStatment.setString(4, lastName);
			registerStatment.setString(5, emailAddress);
			registerStatment.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
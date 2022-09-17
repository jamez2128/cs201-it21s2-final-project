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
	Date dateOfBirth;
	boolean loginSuccess = false;
	boolean isConnected = false;

	public Account(String phoneNumberInput, String pinCodeInput) {
		try {
			Connection localConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/digicash", "mysqluser", "password");
			isConnected = true;
			PreparedStatement state  = localConn.prepareStatement("select * from accounts where phoneNumber = ?");
			state.setString(1, phoneNumberInput);
			ResultSet result = state.executeQuery();
			if (!result.isBeforeFirst()) {
				loginSuccess = false;
				return;
			}
			while (result.next()) {
				phoneNumber = result.getString("phoneNumber");
				pinCode = result.getString("pinCode");
				 if (pinCode.equals(pinCodeInput)) {
					 id = result.getInt("id");
					 lastName = result.getString("lastName");
					 firstName = result.getString("firstName");
					 emailAddress = result.getString("emailAddress");
					 balance = result.getDouble("balance");
					 dateOfBirth = result.getDate("dateOfBirth");
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
}
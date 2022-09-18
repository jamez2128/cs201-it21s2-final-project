package _6_Week_6_Final_Project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

public class Account {
	int id;
	String phoneNumber;
	String pinCode;
	double balance;
	String lastName;
	String firstName;
	String emailAddress;
	boolean loginSuccess = false;
	static boolean isConnected = false;
	
	static Connection localConn;
	
	public static void initializeConnection() {
		if (localConn == null) {
			try {
				localConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/digicash", "mysqluser", "password");
				isConnected = true;
			} catch (SQLException e) {
				localConn = null;
				isConnected = false;
				e.printStackTrace();
			}
		}
	}

	public Account(String phoneNumberInput, String pinCodeInput) {
		initializeConnection();
		try {
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
		} catch (CommunicationsException e) {
			initializeConnection();
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void register(String phoneNumber, String pinCode, String firstName, String lastName, String emailAddress) {
		initializeConnection();
		try {
			PreparedStatement registerStatment = localConn.prepareStatement("insert into accounts (phoneNumber, pinCode, firstName, lastName, emailAddress, balance) values (?, ?, ?, ?, ?, 0);");
			registerStatment.setString(1, phoneNumber);
			registerStatment.setString(2, pinCode);
			registerStatment.setString(3, firstName);
			registerStatment.setString(4, lastName);
			registerStatment.setString(5, emailAddress);
			registerStatment.executeUpdate();
		} catch (CommunicationsException e) {
			initializeConnection();
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isAccountExists(String phoneNumber) {
		initializeConnection();
		boolean itExists = false;
		try {
			PreparedStatement lookForAccount = localConn.prepareStatement("select phoneNumber from accounts where phoneNumber = ? limit 1");
			lookForAccount.setString(1, phoneNumber);
			ResultSet accountResults = lookForAccount.executeQuery();
			if (accountResults.isBeforeFirst()) {
				itExists = true;
			} else {
				itExists = false;
			}
		} catch (CommunicationsException e) {
			initializeConnection();
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return itExists;
	}
}
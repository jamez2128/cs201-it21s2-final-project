package _6_Week_6_Final_Project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import java.util.ArrayList;
import java.util.Date;

class TransactionHistory {
	String description;
	Date date;
	double amount;

	public TransactionHistory(String description, Timestamp date, double amount) {
		this.description = description;
		this.date = date;
		this.amount = amount;
	}
	
}

public class Account {
	int id;
	String phoneNumber;
	String pinCode;
	double balance;
	String lastName;
	String firstName;
	String emailAddress;
	ArrayList<TransactionHistory> transactionHistoryList;
	boolean loginSuccess = false;
	static boolean isConnected = false;
	
	static Connection localConn;

	public void sendMoneyToAnotherUser(String phoneNumber, String description, double amount) {
		try {
			double toUserbalance = 0;
			int toUserId = 0;
			String toUserFirstName = "";
			String toUserLastName = "";
			PreparedStatement getInfoToUser = localConn.prepareStatement("select * from accounts where phoneNumber = ? limit 1;");
			getInfoToUser.setString(1, phoneNumber);
			ResultSet toUserInfoResults = getInfoToUser.executeQuery();
			while (toUserInfoResults.next()) {
				toUserId = toUserInfoResults.getInt("id");
				toUserbalance = toUserInfoResults.getDouble("balance");
				toUserFirstName = toUserInfoResults.getString("firstName");
				toUserLastName = toUserInfoResults.getString("lastName");
			}
			
			transact(amount, "Sent to " + toUserFirstName + " " + " " + toUserLastName + " (+63" + phoneNumber + "): " + description);
			addToHistory(toUserId, "From " + firstName + " " +  lastName + " (+63" + this.phoneNumber + "): " + description, amount);
			changeBalance(toUserId, toUserbalance + amount);
		} catch (CommunicationsException e) {
			initializeConnection();
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void transact(double amount, String description) {
		double newAmount = balance - amount;
		Account.changeBalance(id, newAmount);
		Account.addToHistory(id, description, (amount * (-1)));
	}
	
	public void cashIn(double amount, String description) {
		double newAmount = balance + amount;
		Account.changeBalance(id, newAmount);
		Account.addToHistory(id, description, amount);
	}
	
	public void updateInfo() {
		initializeConnection();
		try {
			PreparedStatement refreshStatment  = localConn.prepareStatement("select * from accounts where id = ? limit 1");
			refreshStatment.setInt(1, id);
			ResultSet refreshResults = refreshStatment.executeQuery();
			while (refreshResults.next()) {
				phoneNumber = refreshResults.getString("phoneNumber");
				pinCode = refreshResults.getString("pinCode");
				id = refreshResults.getInt("id");
				lastName = refreshResults.getString("lastName");
				firstName = refreshResults.getString("firstName");
				emailAddress = refreshResults.getString("emailAddress");
				balance = refreshResults.getDouble("balance");
				initializeTransactionList();
				return;
			}
		} catch (CommunicationsException e) {
			initializeConnection();
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public void initializeTransactionList() {
		transactionHistoryList = new ArrayList<>();
		initializeConnection();
		try {
			PreparedStatement getTransactionStatement = localConn.prepareStatement("select * from transactionHistory where accountId = ? order by id desc;");
			getTransactionStatement.setInt(1, id);
			ResultSet transactionHistoryResults = getTransactionStatement.executeQuery();
			while (transactionHistoryResults.next()) {
				String description = transactionHistoryResults.getString("description");
				Timestamp date = transactionHistoryResults.getTimestamp("date");
				double amount = transactionHistoryResults.getDouble("amount");
				transactionHistoryList.add(new TransactionHistory(description, date, amount));
			}
		} catch (CommunicationsException e) {
			initializeConnection();
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void addToHistory(int accountId, String description, double amount) {
		initializeConnection();
		try {
			PreparedStatement historyStatement = localConn.prepareStatement("insert into transactionHistory (accountId, description, amount) values (?, ?, ?);");
			historyStatement.setInt(1, accountId);
			historyStatement.setString(2, description);
			historyStatement.setDouble(3, amount);
			historyStatement.executeUpdate();
		} catch (CommunicationsException e) {
			initializeConnection();
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void initializeConnection() {
		if (localConn == null) {
			try {
				localConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/digicash", "root", "password");
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
			PreparedStatement loginStatement  = localConn.prepareStatement("select * from accounts where phoneNumber = ? limit 1");
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
					 initializeTransactionList();
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
			PreparedStatement getNewlyCreatedAccountIdStatement = localConn.prepareStatement("select id from accounts order by id desc limit 1;");
			ResultSet idResult = getNewlyCreatedAccountIdStatement.executeQuery();
			while (idResult.next()) {
				addToHistory(idResult.getInt("id"), "Digicash created account", 0);
			}
		} catch (CommunicationsException e) {
			initializeConnection();
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void changeBalance(int id, double amount) {
		try {
			PreparedStatement updateBalanceStatement = localConn.prepareStatement("update accounts set balance = ? where id = ? limit 1;");
			updateBalanceStatement.setDouble(1, amount);
			updateBalanceStatement.setInt(2, id);
			updateBalanceStatement.executeUpdate();
		} catch (CommunicationsException e) {
			initializeConnection();
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isAccountExists(String phoneNumber) {
		initializeConnection();
		boolean itExists = false;
		try {
			PreparedStatement lookForAccount = localConn.prepareStatement("select phoneNumber from accounts where phoneNumber = ? limit 1;");
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
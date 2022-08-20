package net.sliit.studentregister.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sliit.studentregister.model.Payment;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 */
public class PaymentDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_PAYMENTS_SQL = "INSERT INTO payments" + "  (StudentID,Reason,Method,Amount) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_PAYMENT_BY_ID = "select PaymentID,StudentID,Reason,Method,Amount from payments where PaymentID=?;";
	private static final String SELECT_ALL_PAYMENTS = "select * from payments;";
	private static final String DELETE_PAYMENTS_SQL = "delete from payments where PaymentID=?;";
	private static final String UPDATE_PAYMENTS_SQL = "update payments set StudentID=?,Reason=?,Method=?,Amount=? where PaymentID=?;";
	
	public PaymentDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertPayment(Payment payment) throws SQLException {
		System.out.println(INSERT_PAYMENTS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PAYMENTS_SQL)) {
			preparedStatement.setInt(1, payment.getStudentID());
			preparedStatement.setString(2, payment.getReason());
			preparedStatement.setString(3, payment.getMethod());
			preparedStatement.setDouble(4, payment.getAmount());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Payment selectPayment(int id) {
		Payment payment = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int studentID = rs.getInt("StudentID");
				String reason = rs.getString("Reason");
				String method = rs.getString("Method");
				double amount = rs.getDouble("Amount");
				payment = new Payment();
				payment.setPaymentID(id);
				payment.setStudentID(studentID);
				payment.setReason(reason);
				payment.setMethod(method);
				payment.setAmount(amount);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return payment;
	}

	public List<Payment> selectAllPayments() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Payment> payments = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PAYMENTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int paymentID = rs.getInt("PaymentID");
				int studentID = rs.getInt("StudentID");
				String reason = rs.getString("Reason");
				String method = rs.getString("Method");
				double amount = rs.getDouble("Amount");
				payments.add(new Payment(paymentID, studentID, reason, method, amount));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return payments;
	}

	public boolean deletePayment(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_PAYMENTS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updatePayment(Payment payment) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_PAYMENTS_SQL);) {
			statement.setInt(1, payment.getStudentID());
			statement.setString(2, payment.getReason());
			statement.setString(3, payment.getMethod());
			statement.setDouble(4, payment.getAmount());
			statement.setInt(5, payment.getPaymentID());
			System.out.println(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}

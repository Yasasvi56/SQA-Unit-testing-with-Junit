package net.sliit.studentregister.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sliit.studentregister.model.Result;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 */
public class ResultDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_RESULT_SQL = "INSERT INTO results" + " (ExamID,StudentID,Marks) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_RESULT_BY_ID = "select ResultID,ExamID,StudentID,Marks from results where ResultID=?;";
	private static final String SELECT_ALL_RESULT = "select * from results;";
	private static final String DELETE_RESULT_SQL = "delete from results where ResultID=?;";
	private static final String UPDATE_RESULT_SQL = "update results set ExamID=?,StudentID=?,Marks=? where ResultID=?;";
	
	public ResultDAO() {
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

	public void insertResult(Result result) throws SQLException {
		System.out.println(INSERT_RESULT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESULT_SQL)) {
			preparedStatement.setInt(1, result.getExamID());
			preparedStatement.setInt(2, result.getStudentID());
			preparedStatement.setInt(3, result.getMarks());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Result selectResult(int id) {
		Result result = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RESULT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int examID = rs.getInt("ExamID");
				int studentID = rs.getInt("StudentID");
				int marks = rs.getInt("Marks");
				result = new Result();
				result.setResultID(id);
				result.setExamID(examID);
				result.setStudentID(studentID);
				result.setMarks(marks);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	public List<Result> selectAllResults() {
		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Result> results = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_RESULT);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int resultID = rs.getInt("ResultID");
				int examID = rs.getInt("ExamID");
				int studentID = rs.getInt("StudentID");
				int marks = rs.getInt("Marks");
				results.add(new Result(resultID, examID, studentID, marks));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return results;
	}

	public boolean deleteResult(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_RESULT_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateResult(Result result) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_RESULT_SQL);) {
			statement.setInt(1, result.getExamID());
			statement.setInt(2, result.getStudentID());
			statement.setInt(3, result.getMarks());
			statement.setInt(4, result.getResultID());
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

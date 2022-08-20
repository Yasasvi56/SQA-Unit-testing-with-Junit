package net.sliit.studentregister.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sliit.studentregister.model.Exam;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 */
public class ExamDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_EXAM_SQL = "INSERT INTO exams" + "  (SubjectID,Description,ExamDate,Time,Location) VALUES "
			+ " (?, ?, ?, ?, ?);";

	private static final String SELECT_EXAM_BY_ID = "select ExamID,SubjectID,Description,ExamDate,Time,Location from exams where examID=?;";
	private static final String SELECT_ALL_EXAM = "select * from exams;";
	private static final String DELETE_EXAM_SQL = "delete from exams where ExamID=?;";
	private static final String UPDATE_EXAM_SQL = "update exams set SubjectID=?,Description=?,ExamDate=?,Time=?,Location=? where ExamID=?;";
	
	public ExamDAO() {
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

	public void insertExam(Exam exam) throws SQLException {
		System.out.println(INSERT_EXAM_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EXAM_SQL)) {
			preparedStatement.setInt(1, exam.getSubjectID());
			preparedStatement.setString(2, exam.getDescription());
			preparedStatement.setDate(3, exam.getExamDate());
			preparedStatement.setString(4, exam.getTime());
			preparedStatement.setString(5, exam.getLocation());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Exam selectExam(int id) {
		Exam exam = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXAM_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int subjectID = rs.getInt("SubjectID");
				String description = rs.getString("Description");
				Date examDate = rs.getDate("ExamDate");
				String time = rs.getString("Time");
				String location = rs.getString("Location");
				exam = new Exam();
				exam.setExamID(id);
				exam.setSubjectID(subjectID);
				exam.setDescription(description);
				exam.setExamDate(examDate);
				exam.setTime(time);
				exam.setLocation(location);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return exam;
	}

	public List<Exam> selectAllExams() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Exam> exams = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EXAM);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int examID = rs.getInt("ExamID");
				int subjectID = rs.getInt("SubjectID");
				String description = rs.getString("Description");
				Date examDate = rs.getDate("ExamDate");
				String time = rs.getString("Time");
				String location = rs.getString("Location");
				exams.add(new Exam(examID, subjectID, description, examDate, time, location));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return exams;
	}

	public boolean deleteExam(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_EXAM_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateExam(Exam exam) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_EXAM_SQL);) {
			statement.setInt(1, exam.getSubjectID());
			statement.setString(2, exam.getDescription());
			statement.setDate(3, exam.getExamDate());
			statement.setString(4, exam.getTime());
			statement.setString(5, exam.getLocation());
			statement.setInt(6, exam.getExamID());
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

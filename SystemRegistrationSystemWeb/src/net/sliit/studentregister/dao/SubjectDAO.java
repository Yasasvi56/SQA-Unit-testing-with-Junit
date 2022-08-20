package net.sliit.studentregister.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sliit.studentregister.model.Subject;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 */
public class SubjectDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_SUBJECTS_SQL = "INSERT INTO subjects" + "  (SubjectCode,SubjectName) VALUES "
			+ " (?, ?);";

	private static final String SELECT_SUBJECT_BY_ID = "select SubjectID,SubjectCode,SubjectName from subjects where SubjectID =?;";
	private static final String SELECT_ALL_SUBJECTS = "select * from subjects;";
	private static final String DELETE_SUBJECTS_SQL = "delete from subjects where SubjectID = ?;";
	private static final String UPDATE_SUBJECTS_SQL = "update subjects set SubjectCode=?,SubjectName=? where SubjectID=?;";

	public SubjectDAO() {
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

	public boolean insertSubject(Subject subject) throws SQLException {
		System.out.println(INSERT_SUBJECTS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECTS_SQL)) {
			preparedStatement.setString(1, subject.getSubjectCode());
			preparedStatement.setString(2, subject.getSubjectName());
			System.out.println(preparedStatement);
			return preparedStatement.executeUpdate()>0;
		} catch (SQLException e) {
			printSQLException(e);
			return false;
		}
	}

	public Subject selectSubject(int id) {
		Subject subject = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBJECT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String subjectCode = rs.getString("SubjectCode");
				String subjectName = rs.getString("SubjectName");
				subject = new Subject();
				subject.setSubjectID(id);
				subject.setSubjectCode(subjectCode);
				subject.setSubjectName(subjectName);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return subject;
	}

	public List<Subject> selectAllSubjects() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Subject> subjects = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBJECTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int subjectId = rs.getInt("SubjectID");
				String subjectCode = rs.getString("SubjectCode");
				String subjectName = rs.getString("SubjectName");
				subjects.add(new Subject(subjectId, subjectCode, subjectName));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return subjects;
	}

	public boolean deleteSubject(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECTS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateSubject(Subject subject) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECTS_SQL);) {
			statement.setString(1, subject.getSubjectCode());
			statement.setString(2, subject.getSubjectName());
			statement.setInt(3, subject.getSubjectID());
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

package net.sliit.studentregister.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 */
public class ClassDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_CLASSES_SQL = "INSERT INTO classes" + "  (SubjectID,TeacherID,Description,Schedule,Time) VALUES "
			+ " (?, ?, ?, ?, ?);";

	private static final String SELECT_CLASS_BY_ID = "select ClassID,SubjectID,TeacherID,Description,Schedule,Time from classes where ClassID=?;";
	private static final String SELECT_ALL_CLASSES = "select * from classes;";
	private static final String DELETE_CLASSES_SQL = "delete from classes where ClassID=?;";
	private static final String UPDATE_CLASSES_SQL = "update classes set SubjectID=?,TeacherID=?,Description=?,Schedule=?,Time=? where ClassID=?;";

	public ClassDAO() {
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

	public void insertClass(net.sliit.studentregister.model.Class class1) throws SQLException {
		System.out.println(INSERT_CLASSES_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLASSES_SQL)) {
			preparedStatement.setInt(1, class1.getSubjectID());
			preparedStatement.setInt(2, class1.getTeacherID());
			preparedStatement.setString(3, class1.getDescription());
			preparedStatement.setString(4, class1.getSchedule());
			preparedStatement.setString(5, class1.getTime());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public net.sliit.studentregister.model.Class selectClass(int id) {
		net.sliit.studentregister.model.Class class1 = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASS_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int subjectID = rs.getInt("SubjectID");
				int teacherID = rs.getInt("TeacherID");
				String description = rs.getString("Description");
				String schedule = rs.getString("Schedule");
				String time = rs.getString("Time");
				class1 = new net.sliit.studentregister.model.Class();
				class1.setClassID(id);
				class1.setSubjectID(subjectID);
				class1.setTeacherID(teacherID);
				class1.setDescription(description);
				class1.setSchedule(schedule);
				class1.setTime(time);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return class1;
	}

	public List<net.sliit.studentregister.model.Class> selectAllClasses() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<net.sliit.studentregister.model.Class> classes = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLASSES);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int classID = rs.getInt("ClassID");
				int subjectID = rs.getInt("SubjectID");
				int teacherID = rs.getInt("TeacherID");
				String description = rs.getString("Description");
				String schedule = rs.getString("Schedule");
				String time = rs.getString("Time");
				classes.add(new net.sliit.studentregister.model.Class(classID, subjectID, teacherID, description, schedule, time));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return classes;
	}

	public boolean deleteClass(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_CLASSES_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateClass(net.sliit.studentregister.model.Class class1) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLASSES_SQL);) {
			preparedStatement.setInt(1, class1.getSubjectID());
			preparedStatement.setInt(2, class1.getTeacherID());
			preparedStatement.setString(3, class1.getDescription());
			preparedStatement.setString(4, class1.getSchedule());
			preparedStatement.setString(5, class1.getTime());
			preparedStatement.setInt(6, class1.getClassID());
			System.out.println(preparedStatement);
			rowUpdated = preparedStatement.executeUpdate() > 0;
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

package net.sliit.studentregister.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sliit.studentregister.model.Teacher;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 */
public class TeacherDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_TEACHERS_SQL = "INSERT INTO teachers" + "  (Name,Qualification) VALUES "
			+ " (?, ?);";

	private static final String SELECT_TEACHER_BY_ID = "select TeacherID,Name,Qualification from teachers where TeacherID=?;";
	private static final String SELECT_ALL_TEACHERS = "select * from teachers;";
	private static final String DELETE_TEACHERS_SQL = "delete from teachers where TeacherID=?;";
	private static final String UPDATE_TEACHERS_SQL = "update teachers set Name=?,Qualification=? where TeacherID=?;";

	public TeacherDAO() {
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

	public void insertTeacher(Teacher teacher) throws SQLException {
		System.out.println(INSERT_TEACHERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEACHERS_SQL)) {
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getQualification());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Teacher selectTeacher(int id) {
		Teacher teacher = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TEACHER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("Name");
				String qualification = rs.getString("Qualification");
				teacher = new Teacher();
				teacher.setTeacherID(id);
				teacher.setName(name);
				teacher.setQualification(qualification);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return teacher;
	}

	public List<Teacher> selectAllTeachers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Teacher> teachers = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TEACHERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int teacherID = rs.getInt("TeacherID");
				String name = rs.getString("Name");
				String qualification = rs.getString("Qualification");
				teachers.add(new Teacher(teacherID, name, qualification));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return teachers;
	}

	public boolean deleteTeacher(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_TEACHERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateTeacher(Teacher teacher) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_TEACHERS_SQL);) {
			statement.setString(1, teacher.getName());
			statement.setString(2, teacher.getQualification());
			statement.setInt(3, teacher.getTeacherID());
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

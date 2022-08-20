package net.sliit.studentregister.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sliit.studentregister.model.Attendance;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 */
public class AttendanceDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_ATTENDANCE_SQL = "INSERT INTO attendance" + "  (StudentID, ClassID, AttendanceDate) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_ATTENDANCE_BY_ID = "select AttendanceID, StudentID, ClassID, AttendanceDate from attendance where AttendanceID=?;";
	private static final String SELECT_ALL_ATTENDANCE = "select * from attendance;";
	private static final String DELETE_ATTENDANCE_SQL = "delete from attendance where AttendanceID=?;";
	private static final String UPDATE_ATTENDANCE_SQL = "update attendance set StudentID=?,ClassID=?,AttendanceDate=? where AttendanceID=?;";
	
	public AttendanceDAO() {
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

	public void insertAttendance(Attendance attendance) throws SQLException {
		System.out.println(INSERT_ATTENDANCE_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ATTENDANCE_SQL)) {
			preparedStatement.setInt(1, attendance.getStudentID());
			preparedStatement.setInt(2, attendance.getClassID());
			preparedStatement.setDate(3, attendance.getAttendanceDate());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Attendance selectAttendance(int id) {
		Attendance attendance = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ATTENDANCE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int studentID = rs.getInt("StudentID");
				int classID = rs.getInt("ClassID");
				Date attendanceDate = rs.getDate("AttendanceDate");
				attendance = new Attendance();
				attendance.setAttendanceID(id);
				attendance.setStudentID(studentID);
				attendance.setClassID(classID);
				attendance.setAttendanceDate(attendanceDate);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return attendance;
	}

	public List<Attendance> selectAllAttendance() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Attendance> attendances = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ATTENDANCE);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int attendanceID = rs.getInt("AttendanceID");
				int studentID = rs.getInt("StudentID");
				int classID = rs.getInt("ClassID");
				Date attendanceDate = rs.getDate("AttendanceDate");
				attendances.add(new Attendance(attendanceID, studentID, classID, attendanceDate));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return attendances;
	}

	public boolean deleteAttendance(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ATTENDANCE_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateAttendance(Attendance attendance) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_ATTENDANCE_SQL);) {
			statement.setInt(1, attendance.getStudentID());
			statement.setInt(2, attendance.getClassID());
			statement.setDate(3, attendance.getAttendanceDate());
			statement.setInt(4, attendance.getAttendanceID());
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

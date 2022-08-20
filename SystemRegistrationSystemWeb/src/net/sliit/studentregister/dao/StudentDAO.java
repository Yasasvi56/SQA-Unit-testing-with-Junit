package net.sliit.studentregister.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sliit.studentregister.model.Student;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 */
public class StudentDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_STUDENTS_SQL = "INSERT INTO students" + "  (FName,LName,Age,DOB,PhoneNo,Email) VALUES "
			+ " (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_STUDENT_BY_ID = "select StudentID,FName,LName,Age,DOB,PhoneNo,Email from students where StudentID=?;";
	private static final String DELETE_STUDENTS_SQL = "delete from students where StudentID=?;";
	private static final String UPDATE_STUDENTS_SQL = "update students set FName=?,LName=?,Age=?,DOB=?,PhoneNo=?,Email=? where StudentID=?;";

	private static final String SELECT_ALL_STUDENTS = "select * from students;";
	
	public StudentDAO() {
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

	public void insertStudent(Student student) throws SQLException {
		System.out.println(INSERT_STUDENTS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)) {
			preparedStatement.setString(1, student.getFName());
			preparedStatement.setString(2, student.getLName());
			preparedStatement.setInt(3, student.getAge());
			preparedStatement.setDate(4, student.getDOB());
			preparedStatement.setInt(5, student.getPhoneNo());
			preparedStatement.setString(6, student.getEmail());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Student selectStudent(int id) {
		Student student = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String fName = rs.getString("FName");
				String lName = rs.getString("LName");
				int age = rs.getInt("Age");
				Date dob = rs.getDate("DOB");
				int phonenumber = rs.getInt("PhoneNo");
				String email = rs.getString("Email");
				student = new Student();
				student.setStudentID(id);
				student.setFName(fName);
				student.setLName(lName);
				student.setAge(age);
				student.setDOB(dob);
				student.setPhoneNo(phonenumber);
				student.setEmail(email);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}

	public boolean deleteStudent(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENTS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateStudent(Student student) throws SQLException {
		
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENTS_SQL);) {
			statement.setString(1, student.getFName());
			statement.setString(2, student.getLName());
			statement.setInt(3, student.getAge());
			statement.setDate(4, student.getDOB());
			statement.setInt(5, student.getPhoneNo());
			statement.setString(6, student.getEmail());
			statement.setInt(7, student.getStudentID());
			System.out.println(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public List<Student> selectAllStudents() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Student> students = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int studentID = rs.getInt("StudentID");
				String fname = rs.getString("FName");
				String lname = rs.getString("LName");
				int age = rs.getInt("Age");
				int phoneNo = rs.getInt("PhoneNo");
				Date dob = rs.getDate("DOB");
				String email = rs.getString("Email");
				students.add(new Student(studentID, fname, lname, age, dob, phoneNo, email));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return students;
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

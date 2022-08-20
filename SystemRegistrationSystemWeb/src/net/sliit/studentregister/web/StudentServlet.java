package net.sliit.studentregister.web;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sliit.studentregister.dao.StudentDAO;
import net.sliit.studentregister.model.Student;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/student/*")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;
	
	public void init() {
		studentDAO = new StudentDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null){
			action = request.getServletPath();
		}

		try {
			switch (action) {
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insert(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				update(request, response);
				break;
			default:
				list(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Student> listStudent = studentDAO.selectAllStudents();
		request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student existingStudent = studentDAO.selectStudent(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
		request.setAttribute("student", existingStudent);
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		int age = Integer.parseInt(request.getParameter("age"));
		Date dob = Date.valueOf(request.getParameter("dob"));
		int phonenumber = Integer.parseInt(request.getParameter("phonenumber"));
		String email = request.getParameter("email");
		Student student = new Student();
		student.setFName(fname);
		student.setLName(lname);
		student.setAge(age);
		student.setDOB(dob);
		student.setPhoneNo(phonenumber);
		student.setEmail(email);
		studentDAO.insertStudent(student);
		response.sendRedirect("student");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		System.out.println(request);
		int id = Integer.parseInt(request.getParameter("id"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		int age = Integer.parseInt(request.getParameter("age"));
		Date dob = Date.valueOf(request.getParameter("dob"));
		int phonenumber = Integer.parseInt(request.getParameter("phonenumber"));
		String email = request.getParameter("email");
		Student student = new Student();
		student.setStudentID(id);
		student.setFName(fname);
		student.setLName(lname);
		student.setAge(age);
		student.setDOB(dob);
		student.setPhoneNo(phonenumber);
		student.setEmail(email);
		studentDAO.updateStudent(student);
		response.sendRedirect("student");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		studentDAO.deleteStudent(id);
		response.sendRedirect("student");
	}

}

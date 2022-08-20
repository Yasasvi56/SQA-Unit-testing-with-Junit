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

import net.sliit.studentregister.dao.AttendanceDAO;
import net.sliit.studentregister.dao.ClassDAO;
import net.sliit.studentregister.dao.StudentDAO;
import net.sliit.studentregister.model.Attendance;
import net.sliit.studentregister.model.Student;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/attendance/*")
public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AttendanceDAO attendanceDAO;
	private StudentDAO studentDAO;
	private ClassDAO classDAO;
	
	public void init() {
		attendanceDAO = new AttendanceDAO();
		studentDAO = new StudentDAO();
		classDAO = new ClassDAO();
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
		List<Attendance> listAttendance = attendanceDAO.selectAllAttendance();
		request.setAttribute("listAttendance", listAttendance);
		List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
        List<net.sliit.studentregister.model.Class> listClass = classDAO.selectAllClasses();
        request.setAttribute("listClass", listClass);
		RequestDispatcher dispatcher = request.getRequestDispatcher("attendance-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
        List<net.sliit.studentregister.model.Class> listClass = classDAO.selectAllClasses();
        request.setAttribute("listClass", listClass);
		RequestDispatcher dispatcher = request.getRequestDispatcher("attendance-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Attendance existingAttendance = attendanceDAO.selectAttendance(id);
		List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
        List<net.sliit.studentregister.model.Class> listClass = classDAO.selectAllClasses();
        request.setAttribute("listClass", listClass);
		RequestDispatcher dispatcher = request.getRequestDispatcher("attendance-form.jsp");
		request.setAttribute("attendance", existingAttendance);
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		int classID = Integer.parseInt(request.getParameter("classID"));
		Date attendanceDate = Date.valueOf(request.getParameter("attendancedate"));
		Attendance attendance = new Attendance();
		attendance.setStudentID(studentID);
		attendance.setClassID(classID);
		attendance.setAttendanceDate(attendanceDate);
		attendanceDAO.insertAttendance(attendance);
		response.sendRedirect("attendance");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		int classID = Integer.parseInt(request.getParameter("classID"));
		Date attendanceDate = Date.valueOf(request.getParameter("attendancedate"));
		Attendance attendance = new Attendance();
		attendance.setAttendanceID(id);
		attendance.setStudentID(studentID);
		attendance.setClassID(classID);
		attendance.setAttendanceDate(attendanceDate);
		attendanceDAO.updateAttendance(attendance);
		response.sendRedirect("attendance");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		attendanceDAO.deleteAttendance(id);
		response.sendRedirect("attendance");
	}

}

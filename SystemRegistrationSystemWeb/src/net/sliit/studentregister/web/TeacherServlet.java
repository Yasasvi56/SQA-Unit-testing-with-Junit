package net.sliit.studentregister.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sliit.studentregister.dao.TeacherDAO;
import net.sliit.studentregister.model.Teacher;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/teacher/*")
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherDAO teacherDAO;
	
	public void init() {
		teacherDAO = new TeacherDAO();
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
		List<Teacher> listTeacher = teacherDAO.selectAllTeachers();
		request.setAttribute("listTeacher", listTeacher);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Teacher existingTeacher = teacherDAO.selectTeacher(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("teacher-form.jsp");
		request.setAttribute("teacher", existingTeacher);
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String qualifications = request.getParameter("qualifications");
		Teacher teacher = new Teacher();
		teacher.setName(name);
		teacher.setQualification(qualifications);
		teacherDAO.insertTeacher(teacher);
		response.sendRedirect("teacher");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String qualifications = request.getParameter("qualifications");
		Teacher teacher = new Teacher();
		teacher.setTeacherID(id);
		teacher.setName(name);
		teacher.setQualification(qualifications);
		teacherDAO.updateTeacher(teacher);
		response.sendRedirect("teacher");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		teacherDAO.deleteTeacher(id);
		response.sendRedirect("teacher");
	}

}

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

import net.sliit.studentregister.dao.SubjectDAO;
import net.sliit.studentregister.model.Subject;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/subject/*")
public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDAO subjectDAO;
	
	public void init() {
		subjectDAO = new SubjectDAO();
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
				insertSubject(request, response);
				break;
			case "delete":
				deleteSubject(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateSubject(request, response);
				break;
			default:
				listSubject(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listSubject(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Subject> listSubject = subjectDAO.selectAllSubjects();
		request.setAttribute("listSubject", listSubject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Subject existingSubject = subjectDAO.selectSubject(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("subject-form.jsp");
		request.setAttribute("subject", existingSubject);
		dispatcher.forward(request, response);
	}

	private void insertSubject(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String subjectCode = request.getParameter("subjectCode");
		String subjectName = request.getParameter("subjectName");
		Subject subject = new Subject();
		subject.setSubjectCode(subjectCode);
		subject.setSubjectName(subjectName);
		subjectDAO.insertSubject(subject);
		response.sendRedirect("subject");
	}

	private void updateSubject(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String subjectCode = request.getParameter("subjectCode");
		String subjectName = request.getParameter("subjectName");
		Subject subject = new Subject();
		subject.setSubjectID(id);
		subject.setSubjectCode(subjectCode);
		subject.setSubjectName(subjectName);
		subjectDAO.updateSubject(subject);
		response.sendRedirect("subject");
	}

	private void deleteSubject(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		subjectDAO.deleteSubject(id);
		response.sendRedirect("subject");
	}

}

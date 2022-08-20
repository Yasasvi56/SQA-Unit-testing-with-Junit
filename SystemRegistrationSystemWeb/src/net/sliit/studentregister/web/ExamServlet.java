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

import net.sliit.studentregister.dao.ExamDAO;
import net.sliit.studentregister.dao.SubjectDAO;
import net.sliit.studentregister.model.Exam;
import net.sliit.studentregister.model.Subject;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/exam/*")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamDAO examDAO;
	private SubjectDAO subjectDAO;
	
	public void init() {
		examDAO = new ExamDAO();
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
		List<Exam> listExam = examDAO.selectAllExams();
		request.setAttribute("listExam", listExam);
		List<Subject> listSubject = subjectDAO.selectAllSubjects();
        request.setAttribute("listSubject", listSubject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("exam-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Subject> listSubject = subjectDAO.selectAllSubjects();
        request.setAttribute("listSubject", listSubject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("exam-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Exam existingExam = examDAO.selectExam(id);
		List<Subject> listSubject = subjectDAO.selectAllSubjects();
        request.setAttribute("listSubject", listSubject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("exam-form.jsp");
		request.setAttribute("exam", existingExam);
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int subjectID = Integer.parseInt(request.getParameter("subjectID"));
		String description = request.getParameter("description");
		Date examDate = Date.valueOf(request.getParameter("examDate"));
		String time = request.getParameter("time");
		String location = request.getParameter("location");
		Exam exam = new Exam();
		exam.setSubjectID(subjectID);
		exam.setDescription(description);
		exam.setExamDate(examDate);
		exam.setTime(time);
		exam.setLocation(location);
		examDAO.insertExam(exam);
		response.sendRedirect("exam");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int examID = Integer.parseInt(request.getParameter("id"));
		int subjectID = Integer.parseInt(request.getParameter("subjectID"));
		String description = request.getParameter("description");
		Date examDate = Date.valueOf(request.getParameter("examDate"));
		String time = request.getParameter("time");
		String location = request.getParameter("location");
		Exam exam = new Exam();
		exam.setExamID(examID);
		exam.setSubjectID(subjectID);
		exam.setDescription(description);
		exam.setExamDate(examDate);
		exam.setTime(time);
		exam.setLocation(location);
		examDAO.updateExam(exam);
		response.sendRedirect("exam");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		examDAO.deleteExam(id);
		response.sendRedirect("exam");
	}

}

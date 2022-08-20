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

import net.sliit.studentregister.dao.ExamDAO;
import net.sliit.studentregister.dao.ResultDAO;
import net.sliit.studentregister.dao.StudentDAO;
import net.sliit.studentregister.model.Exam;
import net.sliit.studentregister.model.Result;
import net.sliit.studentregister.model.Student;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/result/*")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ResultDAO resultDAO;
	private ExamDAO examDAO;
	private StudentDAO studentDAO;
	
	public void init() {
		resultDAO = new ResultDAO();
		examDAO = new ExamDAO();
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
		List<Result> listResult = resultDAO.selectAllResults();
		request.setAttribute("listResult", listResult);
		List<Exam> listExam = examDAO.selectAllExams();
        request.setAttribute("listExam", listExam);
		List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("result-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Exam> listExam = examDAO.selectAllExams();
        request.setAttribute("listExam", listExam);
		List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("result-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Result existingResult = resultDAO.selectResult(id);
		List<Exam> listExam = examDAO.selectAllExams();
        request.setAttribute("listExam", listExam);
		List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("result-form.jsp");
		request.setAttribute("result", existingResult);
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int examID = Integer.parseInt(request.getParameter("examID"));
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		int marks = Integer.parseInt(request.getParameter("marks"));
		Result result = new Result();
		result.setExamID(examID);
		result.setStudentID(studentID);
		result.setMarks(marks);
		resultDAO.insertResult(result);
		response.sendRedirect("result");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int resultID = Integer.parseInt(request.getParameter("id"));
		int examID = Integer.parseInt(request.getParameter("examID"));
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		int marks = Integer.parseInt(request.getParameter("marks"));
		Result result = new Result();
		result.setResultID(resultID);
		result.setExamID(examID);
		result.setStudentID(studentID);
		result.setMarks(marks);
		resultDAO.updateResult(result);
		response.sendRedirect("result");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		resultDAO.deleteResult(id);
		response.sendRedirect("result");
	}

}

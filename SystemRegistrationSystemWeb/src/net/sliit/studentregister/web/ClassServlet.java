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

import net.sliit.studentregister.dao.ClassDAO;
import net.sliit.studentregister.dao.SubjectDAO;
import net.sliit.studentregister.dao.TeacherDAO;
import net.sliit.studentregister.model.Class;
import net.sliit.studentregister.model.Subject;
import net.sliit.studentregister.model.Teacher;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/class/*")
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassDAO classDAO;
	private SubjectDAO subjectDAO;
	private TeacherDAO teacherDAO;
	
	public void init() {
		classDAO = new ClassDAO();
		subjectDAO = new SubjectDAO();
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
		List<Class> listClass = classDAO.selectAllClasses();
		request.setAttribute("listClass", listClass);
		List<Subject> listSubject = subjectDAO.selectAllSubjects();
        request.setAttribute("listSubject", listSubject);
        List<Teacher> listTeacher = teacherDAO.selectAllTeachers();
        request.setAttribute("listTeacher", listTeacher);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Subject> listSubject = subjectDAO.selectAllSubjects();
        request.setAttribute("listSubject", listSubject);
        List<Teacher> listTeacher = teacherDAO.selectAllTeachers();
        request.setAttribute("listTeacher", listTeacher);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Class existingClass = classDAO.selectClass(id);
		List<Subject> listSubject = subjectDAO.selectAllSubjects();
        request.setAttribute("listSubject", listSubject);
        List<Teacher> listTeacher = teacherDAO.selectAllTeachers();
        request.setAttribute("listTeacher", listTeacher);
		RequestDispatcher dispatcher = request.getRequestDispatcher("class-form.jsp");
		request.setAttribute("classx", existingClass);
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int subjectID = Integer.parseInt(request.getParameter("subjectID"));
		int teacherID = Integer.parseInt(request.getParameter("teacherID"));
		String description = request.getParameter("description");
		String schedule = request.getParameter("schedule");
		String time = request.getParameter("time");
		Class class1 = new Class();
		class1.setSubjectID(subjectID);
		class1.setTeacherID(teacherID);
		class1.setDescription(description);
		class1.setSchedule(schedule);
		class1.setTime(time);
		classDAO.insertClass(class1);
		response.sendRedirect("class");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int subjectID = Integer.parseInt(request.getParameter("subjectID"));
		int teacherID = Integer.parseInt(request.getParameter("teacherID"));
		String description = request.getParameter("description");
		String schedule = request.getParameter("schedule");
		String time = request.getParameter("time");
		Class class1 = new Class();
		class1.setClassID(id);
		class1.setSubjectID(subjectID);
		class1.setTeacherID(teacherID);
		class1.setDescription(description);
		class1.setSchedule(schedule);
		class1.setTime(time);
		classDAO.updateClass(class1);
		response.sendRedirect("class");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		classDAO.deleteClass(id);
		response.sendRedirect("class");
	}

}

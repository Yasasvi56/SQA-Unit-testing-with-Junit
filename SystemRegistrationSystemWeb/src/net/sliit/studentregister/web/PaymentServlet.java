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

import net.sliit.studentregister.dao.PaymentDAO;
import net.sliit.studentregister.dao.StudentDAO;
import net.sliit.studentregister.model.Payment;
import net.sliit.studentregister.model.Student;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/payment/*")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaymentDAO paymentDAO;
	private StudentDAO studentDAO;
	
	public void init() {
		paymentDAO = new PaymentDAO();
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
		List<Payment> listPayment = paymentDAO.selectAllPayments();
		request.setAttribute("listPayment", listPayment);
		List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("payment-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("payment-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Payment existingPayment = paymentDAO.selectPayment(id);
		List<Student> listStudent = studentDAO.selectAllStudents();
        request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("payment-form.jsp");
		request.setAttribute("payment", existingPayment);
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		String reason = request.getParameter("reason");
		String method = request.getParameter("method");
		double amount = Double.parseDouble(request.getParameter("amount"));
		Payment payment = new Payment();
		payment.setStudentID(studentID);
		payment.setReason(reason);
		payment.setMethod(method);
		payment.setAmount(amount);
		paymentDAO.insertPayment(payment);
		response.sendRedirect("payment");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		String reason = request.getParameter("reason");
		String method = request.getParameter("method");
		double amount = Double.parseDouble(request.getParameter("amount"));
		Payment payment = new Payment();
		payment.setPaymentID(id);
		payment.setStudentID(studentID);
		payment.setReason(reason);
		payment.setMethod(method);
		payment.setAmount(amount);
		paymentDAO.updatePayment(payment);
		response.sendRedirect("payment");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		paymentDAO.deletePayment(id);
		response.sendRedirect("payment");
	}

}

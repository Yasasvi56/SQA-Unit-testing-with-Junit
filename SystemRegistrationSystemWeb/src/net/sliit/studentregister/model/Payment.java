package net.sliit.studentregister.model;

public class Payment {
	private int PaymentID;
	private int StudentID;
	private String Reason;
	private String Method;
	private double Amount;
	
	public Payment(int paymentID, int studentID, String reason, String method,
			double amount) {
		super();
		PaymentID = paymentID;
		StudentID = studentID;
		Reason = reason;
		Method = method;
		Amount = amount;
	}
	
	public Payment(int studentID, String reason, String method,
			double amount) {
		super();
		StudentID = studentID;
		Reason = reason;
		Method = method;
		Amount = amount;
	}
	
	public Payment() {
		super();
	}

	public int getPaymentID() {
		return PaymentID;
	}

	public void setPaymentID(int paymentID) {
		PaymentID = paymentID;
	}

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public String getMethod() {
		return Method;
	}

	public void setMethod(String method) {
		Method = method;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}
	
}

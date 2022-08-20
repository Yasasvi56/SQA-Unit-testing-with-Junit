package net.sliit.studentregister.model;

public class Result {
	
	private int ResultID;
	private int ExamID;
	private int StudentID;
	private int Marks;
	
	public Result(int resultID, int examID, int studentID, int marks) {
		super();
		ResultID = resultID;
		ExamID = examID;
		StudentID = studentID;
		Marks = marks;
	}
	
	public Result(int examID, int studentID, int marks) {
		super();
		ExamID = examID;
		StudentID = studentID;
		Marks = marks;
	}
	
	public Result() {
		super();
	}

	public int getResultID() {
		return ResultID;
	}

	public void setResultID(int resultID) {
		ResultID = resultID;
	}

	public int getExamID() {
		return ExamID;
	}

	public void setExamID(int examID) {
		ExamID = examID;
	}

	public int getStudentID() {
		return StudentID;
	}

	public void setStudentID(int studentID) {
		StudentID = studentID;
	}

	public int getMarks() {
		return Marks;
	}

	public void setMarks(int marks) {
		Marks = marks;
	}
	
}
